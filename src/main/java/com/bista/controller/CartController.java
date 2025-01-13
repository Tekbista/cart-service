package com.bista.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bista.entity.Cart;
import com.bista.entity.Product;
import com.bista.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("http://localhost:4200")
public class CartController {


	private final CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping("/greeting")
	public ResponseEntity<String> greeting() {

		return new ResponseEntity<>("Cart service is upon running", HttpStatus.OK);
	}

	@PostMapping("/assignCart")
	public ResponseEntity<String> assignCartToUser() {
		String email = getUserEmail();
		cartService.assignCartToUser(email);
		return new ResponseEntity<>("Cart has been assigned to user.", HttpStatus.CREATED);
	}

	@GetMapping("/getUserCart")
	public ResponseEntity<Cart> getUserCart() {
		String email = getUserEmail();
		Cart userCart = cartService.getUserCart(email);
		return new ResponseEntity<>(userCart, HttpStatus.OK);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Product product) {
		String email = getUserEmail();
		Cart userCart = cartService.addToCart(product, email);
		return new ResponseEntity<>(userCart, HttpStatus.CREATED);
	}

	@PostMapping("/removeProduct")
	public ResponseEntity<Cart> removeProductFromCart(@RequestBody Product product) {
		String email = getUserEmail();
		Cart userCart = cartService.removeProductFromCart(product, email);

		return new ResponseEntity<>(userCart, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/changeQuantity")
	public ResponseEntity<Cart> changeProductQuantity(@RequestBody Product product) {
		String email = getUserEmail();
		Cart userCart = cartService.changeItemQuantity(product, email);
		return ResponseEntity.ok(userCart);
	}

	@PostMapping("/emptyCart")
	public ResponseEntity<Cart> emptyCart() {
		String email = getUserEmail();
		Cart userCart = cartService.emptyCart(email);

		return new ResponseEntity<>(userCart, HttpStatus.NO_CONTENT);
	}

	private String getUserEmail(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt jwt = (Jwt) authentication.getPrincipal();

		return jwt.getClaims().get("email").toString();
	}

}
