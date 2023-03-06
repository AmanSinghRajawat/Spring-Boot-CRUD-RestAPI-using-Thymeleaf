package com.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

	
	
}
