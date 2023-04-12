package com.project.lumos.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.shop.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, String>{

}