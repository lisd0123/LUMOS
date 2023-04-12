package com.project.lumos.product.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import com.project.lumos.product.entity.ImageAndProduct;
import com.project.lumos.product.entity.Product;

public interface ImageAndProductRepository extends JpaRepository<ImageAndProduct, Integer>{

	List<ImageAndProduct> findByMainImg(String status);

	Page<ImageAndProduct> findByMainImg(String status, Pageable paging);

	ImageAndProduct findByPdCodeAndMainImg(int pdCode, String string);

	ImageAndProduct findByPdCode(int pdCode);

	
}
