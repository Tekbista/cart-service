package com.bista.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bista.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByEmail(String email);
	Boolean existsByEmail(String email);
	
}
