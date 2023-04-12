package com.project.lumos.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.product.entity.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage , Integer>{

	List<ProductImage> findByPdCode(int pdCode);

	/* [구도연] */
	ProductImage findByPdCodeAndMainImgLike(int pdCode, String string);

}