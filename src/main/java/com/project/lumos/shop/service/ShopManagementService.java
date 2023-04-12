package com.project.lumos.shop.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.lumos.order.service.OrderService;
import com.project.lumos.shop.dto.CompanyDTO;
import com.project.lumos.shop.dto.ShopDTO;
import com.project.lumos.shop.entity.Company;
import com.project.lumos.shop.entity.Shop;
import com.project.lumos.shop.repository.CompanyRepository;
import com.project.lumos.shop.repository.ShopRepository;

@Service
public class ShopManagementService {

	private static final Logger log = LoggerFactory.getLogger(OrderService.class);
	private final CompanyRepository companyRepository;
	private final ShopRepository shopRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ShopManagementService(CompanyRepository companyRepository, ShopRepository shopRepository, ModelMapper modelMapper) {
		this.companyRepository = companyRepository;
		this.shopRepository = shopRepository;
		this.modelMapper = modelMapper;
	}

	/* 사업자 정보 조회 */
	public Object selectCompanyInfo() {
		/* 사업자 등록 번호 */
		int bsrNum = 1234567890;
		Company companyInfo = companyRepository.findById(bsrNum).get();
		log.info("[companyInfo] {}", companyInfo);
		return modelMapper.map(companyInfo, CompanyDTO.class);
	}
	
	/* 쇼핑몰 정보 조회 */
	public Object selectShopInfo() {
		/* 쇼핑몰 이름 */
		String shopNm = "LUMOS";
		Shop shopInfo = shopRepository.findById(shopNm).get();
		log.info("[shopInfo] {}", shopInfo);
		return modelMapper.map(shopInfo, ShopDTO.class);
	}

	/* 사업자 정보 저장 */
	@Transactional
	public Object updateCompanyInfo(CompanyDTO companyDTO) {
		
		int result = 0;
		
		try {
			Company newCompany = companyRepository.findById(companyDTO.getBsrNum()).get();
			
			newCompany.setBsItem(companyDTO.getBsItem());
			newCompany.setBsType(companyDTO.getBsType());
			newCompany.setCpAds(companyDTO.getCpAds());
			newCompany.setCpAdsDetail(companyDTO.getCpAdsDetail());
			newCompany.setCpAdsNum(companyDTO.getCpAdsNum());
			newCompany.setCpEmail(companyDTO.getCpEmail());
			newCompany.setCpNm(companyDTO.getCpNm());
			newCompany.setCpTel(companyDTO.getCpTel());
			newCompany.setRpNm(companyDTO.getRpNm());
			
			companyRepository.save(newCompany);
			
			result = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return (result > 0) ? "사업자 정보 수정 성공" : "사업자 정보 수정 실패";
		
	}

	/* 쇼핑몰 정보 저장 */
	@Transactional
	public Object updateShopInfo(ShopDTO shopDTO) {
		
		int result = 0;
		
		try {
			Shop newShop = shopRepository.findById(shopDTO.getShopNm()).get();
			
			newShop.setCsEmail(shopDTO.getCsEmail());

			
			shopRepository.save(newShop);
			
			result = 1;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return (result > 0) ? "쇼핑몰 정보 수정 성공" : "쇼핑몰 정보 수정 실패";
		
	}
	
}