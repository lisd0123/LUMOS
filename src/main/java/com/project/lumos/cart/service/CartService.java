package com.project.lumos.cart.service;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.lumos.member.repository.MemberRepository;
import com.project.lumos.order.dto.OrderAndOrderProductAndMemberDTO;
import com.project.lumos.order.dto.OrderDTO;
import com.project.lumos.order.dto.OrderProductDTO;
import com.project.lumos.order.entity.Order;
import com.project.lumos.order.entity.OrderAndOrderProductAndMember;
import com.project.lumos.order.entity.OrderProduct;
import com.project.lumos.order.repository.OrderAndOrderProductAndMemberRepository;
import com.project.lumos.order.repository.OrderProductRepository;
import com.project.lumos.order.repository.OrderRepository;
import com.project.lumos.order.service.OrderService;
import com.project.lumos.product.entity.Option;
import com.project.lumos.product.repository.OptionRepository;
import com.project.lumos.product.repository.ProductImageRepository;
import com.project.lumos.product.repository.ProductRepository;

@Service
public class CartService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	private final MemberRepository memberRepository;
	private final OrderAndOrderProductAndMemberRepository orderAndOrderProductAndMemberRepository;
	private final OrderProductRepository orderProductRepository;
	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;
	private final OptionRepository optionRepository;
	private final ProductImageRepository productImageRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CartService(MemberRepository memberRepository,
			OrderAndOrderProductAndMemberRepository orderAndOrderProductAndMemberRepository,
			OrderProductRepository orderProductRepository, OrderRepository orderRepository,
			ProductRepository productRepository, OptionRepository optionRepository,
			ProductImageRepository productImageRepository, ModelMapper modelMapper) {
		this.memberRepository = memberRepository;
		this.orderAndOrderProductAndMemberRepository = orderAndOrderProductAndMemberRepository;
		this.orderProductRepository = orderProductRepository;
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
		this.optionRepository = optionRepository;
		this.productImageRepository = productImageRepository;
		this.modelMapper = modelMapper;
	}
	
    @Value("${image.image-url}")
    private String IMAGE_URL;
    
	/* [장바구니 생성 및 상품 추가] 장바구니가 없는 경우(N) 생성, 있는 경우 추가 */
	@Transactional
	public Object createAndUpdateCart(String memberId, OrderProductDTO orderProductDTO) {
		
		log.info("[CartService] createAndInsertCart Start ===================================");
		
		int result = 0;
		
		log.info("[CartService] orderProductDTO ▶ {}", orderProductDTO);
		log.info("[CartService] memberId ▶ {}", memberId);
		
		/* 로그인한 회원의 주문상태가 "N"인 장바구니 확인 및 회원정보 추출 */
		int memberCode = memberRepository.findMemberByMemberId(memberId).getMemberCode();
		
		log.info("[CartService] memberCode ▶ {}", memberCode);
		
		// ★★★ 엔티티 자료형과 맞추어 던져주어야함 int X -> Member
		String stOrder = "N";
		Order cart = orderRepository.findByMemberCodeLikeAndStOrderLike(memberCode, stOrder);
		
		log.info("[CartService] cart ▶ {}", cart);
		
		/* 장바구니가 없는 경우 */
		if(cart == null) {
			
			log.info("[CartService] ★ Cart is not exist ★");
			
			OrderDTO newCartDTO = new OrderDTO();
			
			log.info("[CartService] newCartDTO ▶ {}", newCartDTO);
			
			/* 구매전 주문코드 임의 생성 */
			String temporaryOrderCode = UUID.randomUUID().toString().replace("-", "");
			
			/* 주문코드, 합계 금액, 주문상태, 회원코드(회원정보) 매핑 및 장바구니 생성 */
			newCartDTO.setOrderCode(temporaryOrderCode);
			newCartDTO.setTotalPc(0);
			newCartDTO.setStOrder("N");
			newCartDTO.setMemberCode(memberCode);
			
			log.info("[CartService] newCartDTO ▶ {}", newCartDTO);
			
			Order newCart = modelMapper.map(newCartDTO, Order.class);
			
			log.info("[CartService] newCart ▶ {}", newCart);
			
			orderRepository.saveAndFlush(newCart);

			/* 
			 * ↑ [주의사항] 위의 주문 정보를 매핑하여 주문 상품 정보 저장
			 *   saveAndFlush를 사용하여 영속성 컨텍스트의 변경 내용을 디비에 반영함
			 *   => 꼭 그래야 하는 것은 아니지만 만일을 위해 대비함
			 */
			
			orderProductDTO.setOrderNum(newCart.getOrderNum());
			OrderProduct addItem = modelMapper.map(orderProductDTO, OrderProduct.class);
			orderProductRepository.save(addItem);
			
			log.info("[CartService] addItem ▶ {}", addItem);
			
			/* 2개의 save가 순차적으로 이루어질 수 없다는 가정하에 단방향 save 진행 */
			/* jpql을 사용한 조회 단계를 일부러 거쳐 인서트 시킴 */
//			int insertOrderNum = orderRepository.findByOrderCode(orderCode);
//			orderProductDTO.setOrderNum(insertOrderNum);
//			OrderProduct addItem = modelMapper.map(orderProductDTO, OrderProduct.class);
//			orderProductRepository.save(addItem);
			
			result = 1;
			
		} else {
			
			log.info("[CartService] ★ Cart is exist ★");

			/* 추가한 상품이 기존 등록된 상품과 상품코드 및 옵션코드가 일치하는 경우 수량만 증가 시킴 */
			OrderProduct selectResult = orderProductRepository.findByOrderNumAndOpCodeAndPdCodeLike(cart.getOrderNum(), orderProductDTO.getOpCode(), orderProductDTO.getPdCode());
			log.info("[CartService] ★ selectResult ★", selectResult);
			
			// 장바구니에 동일한 상품이 없는 경우
			if(selectResult == null) {
				/* 장바구니가 있는 경우 기존 장바구니 식별 번호(주문식별번호) 추출 후 매핑 */
				orderProductDTO.setOrderNum(cart.getOrderNum());
				OrderProduct addItem = modelMapper.map(orderProductDTO, OrderProduct.class);
				orderProductRepository.save(addItem);
				
				log.info("[CartService] ★ 신규 상품 addItem ▶ {}", addItem);
			// 장바구니에 동일한 상품이 있는 경우
			// 주문 수량 증가 => 기존 수량 + 추가할 수량
			} else {
				selectResult.setOrderAmount(selectResult.getOrderAmount() + orderProductDTO.getOrderAmount());
				OrderProduct addItem = modelMapper.map(selectResult, OrderProduct.class);
				orderProductRepository.save(addItem);
				
				log.info("[CartService] ★ 동일 상품 addItem ▶ {}", addItem);
			}
			
		}
		
        log.info("[CartService] createAndInsertCart End ===================================");
        
        return (result > 0) ? "신규 장바구니 제품 추가 성공" : "기존 장바구니 제품 추가 성공";
        
	}

	/* [장바구니 조회] */
	public Object selectCart(String memberId) {
		
		log.info("[CartService] selectCart Start ===================================");
		
		log.info("[CartService] memberId ▶ " + memberId);
		
		int result = 0;
		
		int memberCode = memberRepository.findMemberByMemberId(memberId).getMemberCode();
		String stOrder = "N";
		Order cart = orderRepository.findByMemberCodeLikeAndStOrderLike(memberCode, stOrder);
		
		OrderAndOrderProductAndMember orderDetail = new OrderAndOrderProductAndMember();
		
		if(cart != null) {
			orderDetail = orderAndOrderProductAndMemberRepository.findByOrderCode(cart.getOrderCode());
			
			/* 이미지 경로 & 상품명 & 옵션명 설정 (관리자가 상품정보를 바꾸어도 즉각 반영됨) */
			List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderNumLike(orderDetail.getOrderNum());
			for(OrderProduct orderProduct : orderProductList) {
				orderProduct.setMainImgPath(IMAGE_URL + productImageRepository.findByPdCodeAndMainImgLike(orderProduct.getPdCode(), "Y").getPdImgPath());
				orderProduct.setPdName(productRepository.findById(orderProduct.getPdCode()).get().getPdName());
				orderProduct.setOpName(optionRepository.findById(orderProduct.getOpCode()).get().getOptionNm());
			}
			
			result = 1;
			
			log.info("[CartService] orderDetail ▶ {}", orderDetail);
		} else {
			log.info("[CartService] 기존 장바구니가 없습니다.");
		}
		
        log.info("[CartService] selectOrderByOrderCode End ===================================");
        
        return (result > 0) ? modelMapper.map(orderDetail, OrderAndOrderProductAndMemberDTO.class) : "기존 장바구니 없음";
        
	}

	/* [장바구니 상품 수량 수정] */
	@Transactional
	public Object updateOrderProductAmount(String memberId, int opCode, int amount) {
		
		log.info("[CartService] updateOrderProductAmount Start ===================================");
		
		int result = 0;
		
		/* 장바구니 정보 조회 (장바구니가 없을 경우를 위해 new로 생성한 뒤 매핑) */
		int memberCode = memberRepository.findMemberByMemberId(memberId).getMemberCode();
		String stOrder = "N";
		Order cart = orderRepository.findByMemberCodeLikeAndStOrderLike(memberCode, stOrder);
		OrderAndOrderProductAndMember orderDetail = new OrderAndOrderProductAndMember();
		
		if(cart != null) {
			/* 장바구니 정보 가져옴 */
			orderDetail = orderAndOrderProductAndMemberRepository.findByOrderCode(cart.getOrderCode());

			/* 주문 제품 정보 가져옴 (옵션에 해당하는 정보이므로, 주문번호와 옵션코드를 같이 조회) */
			OrderProduct orderProductInfo = orderProductRepository.findByOrderNumAndOpCodeLike(orderDetail.getOrderNum(), opCode);

			/* 장바구니 옵션 수량 수정 및 저장 (구매 전 장바구니는 옵션을 조절할 필요 없음 & 옵션 수량보다 높은 수량은 화면단에서 막음) */
			orderProductInfo.setOrderAmount(amount);
			orderProductRepository.save(orderProductInfo);

			result = 1;
			log.info("[CartService] orderDetail ▶ {}", orderDetail.getOrderProductList());
		} else {
			log.info("[CartService] 수량 수정에 실패하였습니다.");
		}
		
        log.info("[CartService] updateOrderProductAmount End ===================================");
        
        return (result > 0) ? modelMapper.map(orderDetail, OrderAndOrderProductAndMemberDTO.class) : "주문 수량 수정 실패";
        
	}

	/* [장바구니 상품 삭제] */
	@Transactional
	public Object deleteOrderProduct(String memberId, String orderPdNum) {

		log.info("[CartService] deleteOrderProduct Start ===================================");
		
		int result = 0;
		
		/* 장바구니 정보 조회 (장바구니가 없을 경우를 위해 new로 생성한 뒤 매핑) */
		int memberCode = memberRepository.findMemberByMemberId(memberId).getMemberCode();
		String stOrder = "N";
		Order cart = orderRepository.findByMemberCodeLikeAndStOrderLike(memberCode, stOrder);
		OrderAndOrderProductAndMember orderDetail = new OrderAndOrderProductAndMember();
		
		if(cart != null) {
			/* 장바구니 정보 가져옴 */
			orderDetail = orderAndOrderProductAndMemberRepository.findByOrderCode(cart.getOrderCode());
			int orderProductAmount = orderDetail.getOrderProductList().size();

			/* 주문 제품 정보 가져옴 (옵션에 해당하는 정보이므로, 주문번호와 옵션코드를 같이 조회) */
			OrderProduct orderProductInfo = orderProductRepository.findById(Integer.valueOf(orderPdNum)).get();

			/* 주문 제품 삭제 */
			orderProductRepository.deleteById(orderProductInfo.getOrderPdNum());
			
			/* 장바구니 삭제 */
			if(orderProductAmount - 1 == 0) {
				log.info("[CartService] 장바구니에 남은 상품 갯수가 0인가? {}", orderProductAmount - 1 == 0);
				orderRepository.delete(cart);
				log.info("[CartService] 장바구니에 남은 상품이 없어 장바구니가 삭제되었습니다.");
			}
			
			result = 1;
			
		} else {
			log.info("[CartService] 상품 삭제를 실패하였습니다.");
		}
		
        log.info("[CartService] deleteOrderProduct End ===================================");
        
        return (result > 0) ? modelMapper.map(orderDetail, OrderAndOrderProductAndMemberDTO.class) : "상품 삭제 실패";
        
	}

	/* [장바구니 상품 구매] */
	@Transactional
	public Object purcahseOrder(String orderCode, OrderDTO orderDTO) {
		
		log.info("[CartService] purcahseOrder Start ===================================");
		
		int result = 0;
		
		/* Step 1 > 해당 장바구니 DTO 가져오기 */
		Order originOrder =  orderRepository.findByOrderCodeLike(orderCode);
		log.info("[CartService] step 1 originOrder", originOrder);
		
		try {
			/* Step 2 > 기존 장바구니에 값 매핑 : cgAds, cgAdsDetail, cgAdsNum, cgNm, cgPh, deliveryMsg, deliveryMt, deliveryPc, orderPc, paymentMt, stOrder, stPayment, totalPc */
			// 주문일은 주문시점을 기준으로 생성
			/* util date 생성 및 포맷 (★★★ 9시간 => OffsetDateTime 활용 ★★★) */
			OffsetDateTime now = OffsetDateTime.now();
			ZoneOffset offset = now.getOffset();
			int seconds = offset.getTotalSeconds();
			OffsetDateTime offsetDateTime = null;
			offsetDateTime = now.plusSeconds(seconds);
			java.sql.Timestamp sqlTimeStamp = java.sql.Timestamp.valueOf(offsetDateTime.toLocalDateTime());
			
			// 주문번호 새로 생성
			/* 주문코드 앞쪽 생성을 위한 로직 */
			java.util.Date nowNew = new java.util.Date();
			SimpleDateFormat sdfNew = new SimpleDateFormat("yyyyMMdd");
			String orderCode1New = sdfNew.format(nowNew);
			
			/* 주문코드 뒷쪽 생성을 위한 로직 */
			String orderCode2New = orderRepository.todayMaxOrderNum();
			String replaceOrderCode2New = "";
			
			log.info("[CartService] replaceOrderCode2 is return ? ▶ {}", replaceOrderCode2New);
			
			if(orderCode2New == null) {
				replaceOrderCode2New = "0";
			} else {
				// ex : 000001 -> 1
				replaceOrderCode2New = orderCode2New.replaceAll("[0]", "");
			}
			
			log.info("[CartService] replaceOrderCode2 ▶ {}", replaceOrderCode2New);
			
			/* 6자리 숫자를 만드는 로직 */
			int diffNew = 6 - replaceOrderCode2New.length();
			int sumOrderCode2New = Integer.valueOf(replaceOrderCode2New) + 1;
			String newOrderCode2New = String.valueOf(sumOrderCode2New);
			StringBuffer bufOrderCode2New = new StringBuffer(newOrderCode2New);
			for(int i = 0; i < diffNew; i++) {
				bufOrderCode2New.insert(i, "0");
			}
			
			/* 주문코드 앞쪽과 뒷쪽 합성 */
			String orderCodeNew = orderCode1New + "-" + bufOrderCode2New;
			log.info("[CartService] orderCodeNew ▶ {}", orderCodeNew);
			
			/* 구매시점의 이미지 경로 & 상품명 & 옵션명 매핑 */
			List<OrderProduct> orderProductList = orderProductRepository.findAllByOrderNumLike(originOrder.getOrderNum());
			for(OrderProduct orderProduct : orderProductList) {
				orderProduct.setMainImgPath(productImageRepository.findByPdCodeAndMainImgLike(orderProduct.getPdCode(), "Y").getPdImgPath());
				orderProduct.setPdName(productRepository.findById(orderProduct.getPdCode()).get().getPdName());
				orderProduct.setOpName(optionRepository.findById(orderProduct.getOpCode()).get().getOptionNm());
			}
			orderProductRepository.saveAll(orderProductList);
			
			/* 사용자 입력 값 저장 */
			originOrder.setCgAds(orderDTO.getCgAds());
			originOrder.setCgAdsDetail(orderDTO.getCgAdsDetail());
			originOrder.setCgAdsNum(orderDTO.getCgAdsNum());
			originOrder.setCgNm(orderDTO.getCgNm());
			originOrder.setCgPh(orderDTO.getCgPh());
			originOrder.setDeliveryMsg(orderDTO.getDeliveryMsg());
			originOrder.setDeliveryMt(orderDTO.getDeliveryMt());
			originOrder.setDeliveryPc(orderDTO.getDeliveryPc());
			originOrder.setOrderPc(orderDTO.getOrderPc());
			originOrder.setPaymentMt(orderDTO.getPaymentMt());
			originOrder.setStOrder(orderDTO.getStOrder());
			originOrder.setStPayment(orderDTO.getStPayment());
			originOrder.setTotalPc(orderDTO.getTotalPc());
			originOrder.setOrderDate(sqlTimeStamp);
			originOrder.setOrderCode(orderCodeNew);
			
			orderRepository.save(originOrder);
			
			log.info("[CartService] step 2 originOrder", originOrder);
			
			/* Step 3 > 옵션 재고수량 수정 */
			/* 옵션 수량 가져옴 (옵션 수량보다 높은 수량은 화면단에서 막음) */
			List<OrderProduct> originOrderProductList = orderProductRepository.findAllByOrderNumLike(originOrder.getOrderNum());
			for(OrderProduct originOrderProduct : originOrderProductList) {
				Option optionInfo = optionRepository.findById(originOrderProduct.getOpCode()).get();
				// 기존 재고 수량 - 주문 수량
				if(optionInfo.getOptionStock() >= originOrderProduct.getOrderAmount()) {
					optionInfo.setOptionStock(optionInfo.getOptionStock() - originOrderProduct.getOrderAmount());
					optionRepository.save(optionInfo);
					
					result = 1;
					
					log.info("[CartService] step 3 optionInfo", optionInfo);
				} else {
					result = 0;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		log.info("[CartService] purcahseOrder End ===================================");
		
		return (result > 0) ? "주문 성공" : "주문 실패";
	}

}