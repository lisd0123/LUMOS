package com.project.lumos.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.lumos.product.entity.ImageAndProductAndOption;

public interface ImageAndProductAndOptionRepository extends JpaRepository<ImageAndProductAndOption , Integer>{

	List<ImageAndProductAndOption> findByPdCode(int pdCode);

}
