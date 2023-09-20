package com.bista.service;

import org.springframework.stereotype.Service;

import com.bista.entity.Cart;
import com.bista.entity.Product;

@Service
public interface CartService {

	void assignCartToUser(String email);
	Cart getUserCart(String email);
	Cart addToCart(Product product, String email);
	Cart removeProductFromCart(Product product, String email);
	Cart emptyCart(String email);
	Cart changeItemQuantity(Product product, String email);
}
