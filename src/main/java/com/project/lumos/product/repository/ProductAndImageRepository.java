package com.project.lumos.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.product.entity.ProductAndImage;

public interface ProductAndImageRepository extends JpaRepository<ProductAndImage, Integer>{


	List<ProductAndImage> findByPdNameContaining(String search);

	
}
