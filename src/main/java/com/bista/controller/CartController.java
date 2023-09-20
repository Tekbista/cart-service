package com.bista.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bista.entity.Cart;
import com.bista.entity.Product;
import com.bista.service.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin("http://localhost:4200")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/greeting")
	public ResponseEntity<String> gretting() {

		return new ResponseEntity<String>("Cart service is upon running", HttpStatus.OK);
	}

	@PostMapping("/assignCart")
	public ResponseEntity<String> assignCartToUser(@RequestParam("email") String email) {
		cartService.assignCartToUser(email);
		return new ResponseEntity<String>("Cart has been assigned to user.", HttpStatus.CREATED);
	}

	@GetMapping("/getUserCart")
	public ResponseEntity<Cart> getUserCart(@RequestParam("email") String email) {
		Cart userCart = cartService.getUserCart(email);
		return new ResponseEntity<Cart>(userCart, HttpStatus.OK);
	}

	@PostMapping("/addProduct")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Product product, @RequestParam("email") String email) {
		Cart userCart = cartService.addToCart(product, email);
		return new ResponseEntity<Cart>(userCart, HttpStatus.CREATED);
	}

	@PostMapping("/removeProduct")
	public ResponseEntity<Cart> removeProductFromCart(@RequestBody Product product,
			@RequestParam("email") String email) {
		Cart userCart = cartService.removeProductFromCart(product, email);

		return new ResponseEntity<Cart>(userCart, HttpStatus.NO_CONTENT);
	}

	@PostMapping("/changeQuantity")
	public ResponseEntity<Cart> changeProductQuantity(@RequestBody Product product,
			@RequestParam("email") String email) {
		Cart userCart = cartService.changeItemQuantity(product, email);
		return ResponseEntity.ok(userCart);
	}

	@PostMapping("/emptyCart")
	public ResponseEntity<Cart> emptyCart(@RequestParam("email") String email) {
		Cart userCart = cartService.emptyCart(email);

		return new ResponseEntity<Cart>(userCart, HttpStatus.NO_CONTENT);
	}

}
