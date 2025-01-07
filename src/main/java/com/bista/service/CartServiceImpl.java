package com.bista.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bista.entity.Cart;
import com.bista.entity.Product;
import com.bista.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{


	private final CartRepository cartRepository;

	public CartServiceImpl(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}

	@Override
	public Cart addToCart(Product product, String email) {
		Cart userCart = getUserCart(email);
		List<Product> products = userCart.getProducts();
		boolean exist = false;
		// TODO Check if the product exist in the cart. If exist, increase the number of quantity else add the product.
		for(Product prod: products) {
			if(prod.getProductId() == product.getProductId()) {
				prod.setQuantity(prod.getQuantity() + product.getQuantity());
				exist = true;
			}
		}
		
		if(!exist) {
			products.add(product);
		}
		
		userCart.setProducts(products);

		
		return cartRepository.save(userCart);
	}

	@Override
	public Cart removeProductFromCart(Product product, String email) {
		
		Cart userCart = getUserCart(email);
		List<Product> products = userCart.getProducts();
		Iterator<Product> itr = products.iterator();
		
		while (itr.hasNext()) {
			if(itr.next().getProductId() == product.getProductId()) {
				itr.remove();
			}
			
		}
		userCart.setProducts(products);
		
		return cartRepository.save(userCart);
		
	}

	@Override
	public Cart emptyCart(String email) {
		Cart userCart = getUserCart(email);
		List<Product> products = userCart.getProducts();
		products.clear();
		
		userCart.setProducts(products);
		return cartRepository.save(userCart);
		
	}

	@Override
	public void assignCartToUser(String email) {
		List<Product> products = new ArrayList<>();
		Cart userCart = new Cart();
		userCart.setEmail(email);
		userCart.setProducts(products);
		cartRepository.save(userCart);
		
	}

	@Override
	public Cart getUserCart(String email) {
		// If cart is not assign to the user, assign the cart to the user
		if(!cartRepository.existsByEmail(email)) {
			assignCartToUser(email);
		}
		return cartRepository.findByEmail(email);
	}

	@Override
	public Cart changeItemQuantity(Product product, String email) {
		
		Cart userCart = getUserCart(email);
		List<Product> products = userCart.getProducts();
		
		// TODO Check if the product exist in the cart. If exist, increase the number of quantity else add the product.
		for(Product prod: products) {
			if(prod.getProductId() == product.getProductId()) {
				prod.setQuantity(product.getQuantity());
				
			}
		}
		
		userCart.setProducts(products);
		
		return cartRepository.save(userCart);
		
	}
	
	
}
