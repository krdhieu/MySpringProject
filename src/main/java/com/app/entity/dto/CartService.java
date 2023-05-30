package com.app.entity.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class CartService {
    @Autowired
    CartLogic cartLogic;

    public Cookie addProductToCart(Cart currentCart, long productId, int quantity) {
        return cartLogic.saveCartIntoCookie(cartLogic.addProductToCart(currentCart, productId, quantity));
    }

    public Cookie insertNewCartIntoCookie() {
        return cartLogic.saveCartIntoCookie(new Cart());
    }

    public Cart decodeCartFromEncodedJson(String cookieValue) {
        return cartLogic.decodeCartFromEncodedJson(cookieValue);
    }

    public Cookie deleteProductInCart(Long productId, Cart currentCart) {
        return cartLogic.saveCartIntoCookie(cartLogic.deleteProductInCart(productId, currentCart));
    }

    public Cart decodeCartFromCookie(Cookie[] cookies) {
        Cart currentCart = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                currentCart = this.decodeCartFromEncodedJson(cookie.getValue());
            }
        }
        return currentCart;
    }

}
