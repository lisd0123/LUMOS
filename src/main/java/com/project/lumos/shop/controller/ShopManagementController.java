package com.project.lumos.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lumos.common.ResponseDTO;
import com.project.lumos.order.controller.OrderController;
import com.project.lumos.shop.dto.CompanyDTO;
import com.project.lumos.shop.dto.ShopDTO;
import com.project.lumos.shop.service.ShopManagementService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/v1")
public class ShopManagementController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	private final ShopManagementService shopManagementService;
	
	@Autowired
	public ShopManagementController(ShopManagementService shopManagementService) {
		this.shopManagementService = shopManagementService;
	}

	/* 사업자 정보 조회 */
	@Operation(summary = "[상점관리]", description = "사업장 정보 조회", tags = {"OrderController"})
	@GetMapping(value = {"/company-management"})
	public ResponseEntity<ResponseDTO> selectCompanyInfo() {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사업자 정보 조회 성공",  shopManagementService.selectCompanyInfo()));
	}
	
	/* 쇼핑몰 정보 조회 */
	@Operation(summary = "[상점관리]", description = "쇼핑몰 정보 조회", tags = {"OrderController"})
	@GetMapping(value = {"/shop-management"})
	public ResponseEntity<ResponseDTO> selectShopInfo() {
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "쇼핑몰 정보 조회 성공",  shopManagementService.selectShopInfo()));
	}
	
	/* 사업자 정보 변경 */
	@Operation(summary = "[상점관리]", description = "사업장 정보 저장", tags = {"OrderController"})
	@PutMapping(value = {"/company-management/update"})
	public ResponseEntity<ResponseDTO> updateCompanyInfo(@ModelAttribute CompanyDTO companyDTO) {
		log.info("[companyDTO] {}", companyDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "사업자 정보 조회 성공",  shopManagementService.updateCompanyInfo(companyDTO)));
	}
	
	/* 쇼핑몰 정보 변경 */
	@Operation(summary = "[상점관리]", description = "쇼핑몰 정보 저장", tags = {"OrderController"})
	@PutMapping(value = {"/shop-management/update"})
	public ResponseEntity<ResponseDTO> updateShopInfo(@ModelAttribute ShopDTO shopDTO) {
		log.info("[shopDTO] {}", shopDTO);
		return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "쇼핑몰 정보 조회 성공",  shopManagementService.updateShopInfo(shopDTO)));
	}
	
}