package com.project.lumos.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.product.entity.ProductAndImageAndOption;

public interface ProductAndImageAndOptionRepository extends JpaRepository<ProductAndImageAndOption, Integer> {

}