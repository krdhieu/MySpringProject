package com.app.http;

import com.app.entity.dto.Cart;
import com.app.entity.dto.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/add-product/{productId}/{quantity}")
    public @ResponseBody ResponseEntity<?> addProductToCart(HttpServletRequest request,
                                                            @PathVariable("productId") Long productId,
                                                            @PathVariable("quantity") Integer quantity
    ) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cart currentCart = cartService.decodeCartFromCookie(request.getCookies());
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartService.addProductToCart(currentCart, productId, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-product/{productId}")
    public @ResponseBody ResponseEntity<?> deleteProductInCart(HttpServletRequest request,
                                                               @PathVariable("productId") Long productId
    ) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Cart currentCart = cartService.decodeCartFromCookie(cookies);
        if (currentCart == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(cartService.deleteProductInCart(productId, currentCart), HttpStatus.OK);
    }
}
