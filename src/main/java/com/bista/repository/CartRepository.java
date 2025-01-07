package com.bista.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bista.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByEmail(String email);
	Boolean existsByEmail(String email);
	
}
