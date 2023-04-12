package com.project.lumos.product.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.lumos.common.Criteria;
import com.project.lumos.product.dto.ImageAndProductAndOptionDTO;
import com.project.lumos.product.dto.ImageAndProductDTO;
import com.project.lumos.product.dto.OptionDTO;
import com.project.lumos.product.dto.ProductAndImageDTO;
import com.project.lumos.product.dto.ProductDTO;
import com.project.lumos.product.dto.ProductImageDTO;
import com.project.lumos.product.dto.ProductInsertDTO;
import com.project.lumos.product.entity.ImageAndProduct;
import com.project.lumos.product.entity.ImageAndProductAndOption;
import com.project.lumos.product.entity.Option;
import com.project.lumos.product.entity.Product;
import com.project.lumos.product.entity.ProductAndImage;
import com.project.lumos.product.entity.ProductImage;
import com.project.lumos.product.entity.ProductInsert;
import com.project.lumos.product.repository.ImageAndProductAndOptionRepository;
import com.project.lumos.product.repository.ImageAndProductRepository;
import com.project.lumos.product.repository.OptionRepository;
import com.project.lumos.product.repository.ProductAndImageRepository;
import com.project.lumos.product.repository.ProductImageRepository;
import com.project.lumos.product.repository.ProductInsertRepository;
import com.project.lumos.product.repository.ProductRepository;
import com.project.lumos.util.FileUploadUtils;


@Service
public class ProductService {
	
	private static final Logger log = LoggerFactory.getLogger(ProductService.class);
	private final ProductRepository productRepository;
	private final ProductAndImageRepository productAndImageRepository;
	private final ImageAndProductRepository imageAndProductRepository;
	private final ImageAndProductAndOptionRepository imageAndProductAndOptionRepository;
	private final ProductImageRepository productImageRepository;
	private final ProductInsertRepository productInsertRepository;
	private final OptionRepository optionRepository;
	private final ModelMapper modelMapper;

	/* 이미지 저장 할 위치 및 응답 할 이미지 주소(WebConfig 설정파일 추가하기) */
    @Value("${image.image-dir}")
    private String IMAGE_DIR;
    
    @Value("${image.image-url}")
    private String IMAGE_URL;
	
	@Autowired
    public ProductService(ProductInsertRepository productInsertRepository, ProductRepository productRepository,OptionRepository optionRepository, ImageAndProductRepository imageAndProductRepository,ProductAndImageRepository productAndImageRepository, ImageAndProductAndOptionRepository imageAndProductAndOptionRepository,ProductImageRepository productImageRepository,  ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
		this.productAndImageRepository = productAndImageRepository;
		this.imageAndProductRepository = imageAndProductRepository;
		this.imageAndProductAndOptionRepository = imageAndProductAndOptionRepository;
		this.productImageRepository = productImageRepository;
		this.optionRepository = optionRepository;
		this.productInsertRepository = productInsertRepository;
	}

	public int selectProductTotal() {
        log.info("[ProductService] selectProductTotal Start ===================================");
        
        /* 페이징 처리 결과를 Page 타입으로 반환받음 */
        List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
        
        log.info("[ProductService] selectProductTotal End ===================================");
        
        return productList.size();
    }

	public Object selectProductListWithPaging(Criteria cri) {
		log.info("[ProductService] selectProductListWithPaging Start ===================================");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count, Sort.by("pdCode").descending());

        Page<ImageAndProduct> result = imageAndProductRepository.findByMainImg("Y", paging);
        List<ImageAndProduct> productList = (List<ImageAndProduct>)result.getContent();
        
        log.info("productList" + productList);
        
        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
        }
        
        log.info("[ProductService] selectProductListWithPaging End ===================================");
        
        return productList.stream().map(product -> modelMapper.map(product, ImageAndProductDTO.class)).collect(Collectors.toList());
	}

	public Object selectProduct(int pdCode) {
		log.info("[ProductService] selectProduct Start ===================================");
		
		ProductAndImage productList = productAndImageRepository.findById(pdCode).get();
		
		List<ProductImage> imageList = productImageRepository.findByPdCode(pdCode);
		
		
		for(ProductImage image : imageList) {
			image.setPdImgPath(IMAGE_URL + image.getPdImgPath());
			modelMapper.map(imageList, ProductImageDTO.class);
		}
		
		log.info("selectProduct " + productList);
		log.info("[ProductService] selectProduct End ===================================");
		
		return modelMapper.map(productList, ProductAndImage.class);
	}
	
	public Object selectSearchProductList(String search) {
		log.info("[ProductService] selectSearchProductList Start ===================================");
        log.info("[ProductService] searchValue : " + search);
        
        List<ProductAndImage> SearchValue = productAndImageRepository.findByPdNameContaining(search);
        List<ProductImage> imageList = productImageRepository.findAll();
        
        log.info("SearchValue1" + SearchValue);
        
        for(ProductImage image : imageList) {
        	for(int i = 0; i < SearchValue.size();i++) {
        		if(image.getPdCode() == SearchValue.get(i).getPdCode()) {
        			image.setPdImgPath(IMAGE_URL + image.getPdImgPath());
        		}
        	}
        }            
        log.info("SearchValue2" + SearchValue);
        
        log.info("[ProductService] selectSearchProductList End ===================================");

        return SearchValue.stream().map(product -> modelMapper.map(product, ProductAndImageDTO.class)).collect(Collectors.toList());
	}
	
	@Transactional
	public Object insertProduct(ProductInsertDTO productInsertDTO, MultipartFile productImage) {
        log.info("[ProductService] insertProduct Start ===================================");
        log.info("[ProductService] INSERT : " + productInsertDTO);
        
        String imageName = UUID.randomUUID().toString().replace("-", "");
        String replaceFileName = null;
        int result = 0;
        
        ProductDTO pdDTO = new ProductDTO();
        OptionDTO optDTO = new OptionDTO();
        ProductImageDTO imageDTO = new ProductImageDTO();
        
        pdDTO.setPdName(productInsertDTO.getPdName());
        pdDTO.setPdPrice(productInsertDTO.getPdPrice());
        pdDTO.setPdDesc(productInsertDTO.getPdDesc());
        pdDTO.setCatMain(productInsertDTO.getCatMain());
        pdDTO.setCatSub(productInsertDTO.getCatSub());
        
        log.info("[ProductService] pdDTO : " + pdDTO);        	
        
        Product insertProduct = modelMapper.map(pdDTO, Product.class);        	
        log.info("insertProduct : " + insertProduct);        	
        productRepository.saveAndFlush(insertProduct);        	
        
        int i = insertProduct.getPdCode();
        
        try {
        	replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
        	
        	optDTO.setOptionNm(productInsertDTO.getOptionNm());
        	optDTO.setOptionStock(productInsertDTO.getOptionStock());
        	optDTO.setPdCode(i);
        	
        	log.info("[ProductService] optDTO : " + optDTO);
        	
        	Option op = modelMapper.map(optDTO, Option.class);
    		optionRepository.save(op);
    		
    		imageDTO.setPdImgPath(replaceFileName);
    		imageDTO.setMainImg(productInsertDTO.getMainImg());
    		imageDTO.setPdCode(i);
    		log.info("[ProductService] imageDTO : " + imageDTO);
    		
    		ProductImage im = modelMapper.map(imageDTO, ProductImage.class);
    		productImageRepository.save(im);
        	
        	log.info("pdcode   : " + i);
        	
            log.info("[ProductService] insert Image Name : ", replaceFileName);
            
            result = 1;

        } catch (Exception e) {
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        
        
        return (result > 0) ? "상품 입력 성공" : "상품 입력 실패";
	}
	
	public int selectProductTotalForAdmin() {
		log.info("[ProductService] selectProductTotalForAdmin Start ===================================");
		
        int result = productRepository.findAll().size();

        log.info("[ProductService] selectProductTotalForAdmin End ===================================");
        
        return result;
	}

	public Object selectProductListWithPagingForAdmin() {
		log.info("[ProductService] selectProductListWithPagingForAdmin Start ===================================");
//		int index = cri.getPageNum() - 1;
//        int count = cri.getAmount(); 
//        Pageable paging = PageRequest.of(index, count, Sort.by("pdCode").descending());

//        Page<ImageAndProduct> result = imageAndProductRepository.findAll(paging);
//        List<ImageAndProduct> productList = (List<ImageAndProduct>)result.getContent();
		List<ImageAndProduct> productList = imageAndProductRepository.findAll(Sort.by("pdCode").descending());		
		
    	log.info("productList" + productList);
        
        for(int i = 0 ; i < productList.size() ; i++) {
            productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
        }
        
        log.info("[ProductService] selectProductListWithPagingForAdmin End ===================================");
        
        return productList.stream().filter(r -> r.getMainImg().equals("Y")).map(product -> modelMapper.map(product, ImageAndProductDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectProductForAdmin(int imgNum) {
		log.info("[ProductService] selectProductForAdmin Start ===================================");
		
		ImageAndProductAndOption productList = imageAndProductAndOptionRepository.findById(imgNum).get();
		
//		List<ProductImage> imageList = productImageRepository.findByPdCode(pdCode);
//		
//		
//		for(ProductImage image : imageList) {
//			image.setPdImgPath(IMAGE_URL + image.getPdImgPath());
//			modelMapper.map(imageList, ProductImageDTO.class);
//		}
		
		productList.setPdImgPath(IMAGE_URL + productList.getPdImgPath());
		
		log.info("selectProduct " + productList);
		log.info("[ProductService] selectProductForAdmin End ===================================");
		
		return modelMapper.map(productList, ImageAndProductAndOptionDTO.class);
	}
	
	@Transactional
	public Object deleteProduct(int imgNum) {
		log.info("[ProductService] deleteProduct Start ===================================");
		ImageAndProductAndOption productList = imageAndProductAndOptionRepository.findById(imgNum).get();
		
		imageAndProductAndOptionRepository.deleteById(imgNum);	
		FileUploadUtils.deleteFile(IMAGE_DIR, productList.getPdImgPath());
		
		
		log.info("[ProductService] deleteProduct End ===================================");
		return modelMapper.map(productList, ImageAndProductAndOptionDTO.class);
	}
	
	@Transactional
	public Object updateProduct(ProductInsertDTO productInsertDTO, MultipartFile productImage) {
		log.info("[ProductService] updateProduct Start ===================================");
        log.info("[ProductService] productDTO : " + productInsertDTO);
        
        String replaceFileName = null;
        String oriImage = null;
        int result = 0;

        try {
        	
        	/* update 할 엔티티 조회 */
        	
        	ProductInsert product = productInsertRepository.findById(productInsertDTO.getPdCode()).get();
        	
        	List<ProductImage> imageList = productImageRepository.findByPdCode(productInsertDTO.getPdCode());    		
    		
    		for(ProductImage image : imageList) {
    			oriImage = image.getPdImgPath();			
    		}
        	
            log.info("[updateProduct] oriImage : " + oriImage);
            
            /* update를 위한 엔티티 값 수정 */
            
            for(ProductImage upimage : product.getProductImage()) {
            	upimage.setPdImgPath(productInsertDTO.getPdImgPath());
            	if(productImage != null){
                    String imageName = UUID.randomUUID().toString().replace("-", "");
                    replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, productImage);
                    log.info("[updateProduct] InsertFileName : " + replaceFileName);
                    
                    upimage.setPdImgPath(replaceFileName);	// 새로운 파일 이름으로 update
                    log.info("[updateProduct] deleteImage : " + oriImage);
                    
                    boolean isDelete = FileUploadUtils.deleteFile(IMAGE_DIR, oriImage);
                    log.info("[update] isDelete : " + isDelete);
                } else {
                	
                    /* 이미지 변경 없을 시 */
                	upimage.setPdImgPath(oriImage);
                }
            }
            
            for(Option upoption : product.getProductOption()) {
            	upoption.setOptionStock(productInsertDTO.getOptionStock());
            	upoption.setOptionNm(productInsertDTO.getOptionNm());
            }
            
            product.setPdName(productInsertDTO.getPdName());
            product.setPdPrice(productInsertDTO.getPdPrice());
            product.setCatMain(productInsertDTO.getCatMain());
            product.setCatSub(productInsertDTO.getCatSub());
            product.setPdDesc(productInsertDTO.getPdDesc());
            
            

            result = 1;
        } catch (IOException e) {
            log.info("[updateProduct] Exception!!");
            FileUploadUtils.deleteFile(IMAGE_DIR, replaceFileName);
            throw new RuntimeException(e);
        }
        log.info("[ProductService] updateProduct End ===================================");
        return (result > 0) ? "상품 업데이트 성공" : "상품 업데이트 실패";
	}
	
	public Object selectProductListAboutLed() {
        List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
        
        for(int i = 0 ; i < productList.size() ; i++) {
            if(productList.get(i).getProduct().getCatMain().equals("가정용 LED")) {
            	productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
            }
        }
        
        return productList.stream().filter(res -> res.getProduct().getCatMain().equals("가정용 LED")).map(r->modelMapper.map(r, ImageAndProductDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectProductListAboutLamp() {
		List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
              
        for(int i = 0 ; i < productList.size() ; i++) {
            if(productList.get(i).getProduct().getCatMain().equals("램프")) {
            	productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
            }
        }
        
        return productList.stream().filter(res -> res.getProduct().getCatMain().equals("램프")).map(r->modelMapper.map(r, ImageAndProductDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectProductListAboutPendant() {
		List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
              
        for(int i = 0 ; i < productList.size() ; i++) {
            if(productList.get(i).getProduct().getCatMain().equals("식탁등")) {
            	productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
            }
        }
        
        return productList.stream().filter(res -> res.getProduct().getCatMain().equals("식탁등")).map(r->modelMapper.map(r, ImageAndProductDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectProductListAboutDownlight() {
		List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
              
        for(int i = 0 ; i < productList.size() ; i++) {
            if(productList.get(i).getProduct().getCatMain().equals("매입등")) {
            	productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
            }
        }
        
        return productList.stream().filter(res -> res.getProduct().getCatMain().equals("매입등")).map(r->modelMapper.map(r, ImageAndProductDTO.class)).collect(Collectors.toList());
	}
	
	public Object selectProductListAboutSwitch() {
		List<ImageAndProduct> productList = imageAndProductRepository.findByMainImg("Y");
              
        for(int i = 0 ; i < productList.size() ; i++) {
            if(productList.get(i).getProduct().getCatMain().equals("스위치/콘센트")) {
            	productList.get(i).setPdImgPath(IMAGE_URL + productList.get(i).getPdImgPath());
            }
        }
        
        return productList.stream().filter(res -> res.getProduct().getCatMain().equals("스위치/콘센트")).map(r->modelMapper.map(r, ImageAndProductDTO.class)).collect(Collectors.toList());
	}

	
}
