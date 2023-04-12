package com.project.lumos.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.lumos.common.Criteria;
import com.project.lumos.common.PageDTO;
import com.project.lumos.common.PagingResponseDTO;
import com.project.lumos.common.ResponseDTO;
import com.project.lumos.product.dto.ProductDTO;
import com.project.lumos.product.dto.ProductInsertDTO;
import com.project.lumos.product.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "상품 리스트 조회 요청", description = "상품 조회 및 페이징 처리가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products")
	public ResponseEntity<ResponseDTO> selectProductListWithPaging(
			@RequestParam(name = "offset", defaultValue = "1") String offset) {
 
		/* common 패키지에 Criteria, PageDTO, PagingResponseDTO 추가 */
		log.info("[ProductController] selectProductListWithPaging : " + offset);

		int total = productService.selectProductTotal();
		
		Criteria cri = new Criteria(Integer.valueOf(offset), 10);
		PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
		/* 1. offset의 번호에 맞는 페이지에 뿌릴 Product들 */
		pagingResponseDTO.setData(productService.selectProductListWithPaging(cri));
		
		/* 2. PageDTO(Criteria(보고싶은 페이지, 한페이지에 뿌릴 개수), 전체 상품 수) : 화면에서 페이징 처리에 필요한 개념들을 더 계산해서 추출함 */
		pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTO));
	}
	
	@Operation(summary = "상품 상세 조회 요청", description = "상품의 상세 페이지 처리가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/{pdCode}")
	public ResponseEntity<ResponseDTO> selectProductDetail(@PathVariable int pdCode) {
		log.info("123123123"+ pdCode);
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 상세정보 조회 성공",  productService.selectProduct(pdCode)));
	}
	
	@Operation(summary = "상품 등록 요청", description = "해당 상품 등록이 진행됩니다.", tags = { "ProductController" })
    @PostMapping(value = "/products")
	public ResponseEntity<ResponseDTO> insertProduct(@ModelAttribute ProductInsertDTO productInsertDTO, MultipartFile productImage) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 입력 성공",  productService.insertProduct(productInsertDTO, productImage)));
    }
	
	@Operation(summary = "관리자 페이지 상품 리스트 조회 요청", description = "관리자 페이지에서 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products-management")
    public ResponseEntity<ResponseDTO> selectProductListWithPagingForAdmin(
//    		@RequestParam(name="offset", defaultValue="1") String offset
    		) {
//        log.info("[ProductController] selectProductListWithPagingForAdmin : " + offset);
//        int total = productService.selectProductTotalForAdmin();
//        
//        Criteria cri = new Criteria(Integer.valueOf(offset), 10);
//        PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
//        pagingResponseDTO.setData(productService.selectProductListWithPagingForAdmin());
//        pagingResponseDTO.setPageInfo(new PageDTO(cri, total));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", productService.selectProductListWithPagingForAdmin()));
    }
	
	@Operation(summary = "상품 수정 요청", description = "해당 상품 수정이 진행됩니다.", tags = { "ProductController" })
	@PutMapping(value = "/products")
	public ResponseEntity<ResponseDTO> updateProduct(@ModelAttribute ProductInsertDTO productInsertDTO, MultipartFile productImage) {
		
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 수정 성공",  productService.updateProduct(productInsertDTO, productImage)));
	}
    
	@Operation(summary = "관리자 페이지 상품 상세 페이지 조회 요청", description = "관리자 페이지에서 상품 상세 페이지 조회가 진행됩니다.", tags = { "ProductController" })
    @GetMapping("/products-management/{imgNum}")
    public ResponseEntity<ResponseDTO> selectProductDetailForAdmin(@PathVariable int imgNum) {
		
		log.info("11111111111111111111111111111111111111111");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "관리자 페이지 상세정보 조회 성공",  productService.selectProductForAdmin(imgNum)));
    }
	
	@Operation(summary = "상품 삭제 요청", description = "상품 삭제.", tags = { "ProductController" })
	@DeleteMapping("/products/{imgNum}")
	public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable int imgNum) {
		
		log.info("11111111111111111111111111111111111111111");

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "상품 삭제 성공",  productService.deleteProduct(imgNum)));
    }
    
	@Operation(summary = "LED 리스트 조회 요청", description = "LED에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/led")
    public ResponseEntity<ResponseDTO> selectProductListAboutLed() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutLed()));
    }
	
	@Operation(summary = "LAMP 리스트 조회 요청", description = "LAMP에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/lamp")
    public ResponseEntity<ResponseDTO> selectProductListAboutLamp() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutLamp()));
    }
	
	@Operation(summary = "PENDANT 리스트 조회 요청", description = "PENDANT에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/pendant")
    public ResponseEntity<ResponseDTO> selectProductListAboutPendant() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutPendant()));
    }
	
	@Operation(summary = "DOWNLIGHT 리스트 조회 요청", description = "DOWNLIGHT에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/downlight")
    public ResponseEntity<ResponseDTO> selectProductListAboutDownlight() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutDownlight()));
    }
	
	@Operation(summary = "SWITCH 리스트 조회 요청", description = "SWITCH에 해당하는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/switch")
    public ResponseEntity<ResponseDTO> selectProductListAboutSwitch() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectProductListAboutSwitch()));
    }
	
	@Operation(summary = "검색 상품 리스트 조회 요청", description = "검색어에 해당되는 상품 리스트 조회가 진행됩니다.", tags = { "ProductController" })
	@GetMapping("/products/search")
    public ResponseEntity<ResponseDTO> selectSearchProductList(@RequestParam(name="s", defaultValue="all") String search) {

		log.info("search" + search);
		
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  productService.selectSearchProductList(search)));
    }
	
	

}
