package com.project.lumos.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_PRODUCT")
public class ProductAndCategory {
	@Id
	@Column(name = "PRODUCT_CODE")
	private int productCode;
	
	@Column(name = "PRODUCT_NAME")
    private String productName;
	
	@Column(name = "PRODUCT_PRICE")
    private String productPrice;
	
	@Column(name = "PRODUCT_DESCRIPTION")
    private String productDescription;
	
	@Column(name = "PRODUCT_ORDERABLE")
    private String productOrderable;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY_CODE")
	private Option option;

	@Column(name = "PRODUCT_IMAGE_URL")
    private String productImageUrl;
	
	@Column(name = "PRODUCT_STOCK")
    private Long productStock;

	
}
