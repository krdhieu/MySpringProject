package com.app.entity.dto;

import com.app.entity.Product;
import com.app.logic.ProductLogic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.temporal.ChronoUnit;
import java.util.Base64;

import com.google.gson.Gson;

@Component
public class CartLogic {
    @Autowired
    ProductLogic productLogic;
    
    public Cart decodeCartFromJson(String encodedJson) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedJson);
        String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);
        Cart cart = new Gson().fromJson(decodedJson, Cart.class);
        return cart;
    }

    public Cart addProductToCart(HttpServletRequest request, long productId, int quantity) {
        Cookie[] cookies = request.getCookies();
        Cart currentCart = new Cart();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("cart")) {
                System.out.println("name: " + cookie.getName() + "; value: " + cookie.getValue());
                currentCart = this.decodeCartFromJson(cookie.getValue());
            }
        }
        Product product = productLogic.findProductById(productId);
        if (product == null)
            return currentCart;
        if (currentCart.getCartItems().containsKey(productId)) {
            if (currentCart.getCartItems().get(productId) != quantity) {
                currentCart.getCartItems().replace(productId, quantity);
            }
        } else {
            currentCart.getCartItems().put(productId, quantity);
        }
        return currentCart;
    }

    //todo create update cart
    public Cart updateCart(Cart newCart, Cart oldCart) {
        return null;
    }

    public Cookie insertNewCartIntoCookie() {
        String json = new Gson().toJson(new Cart());
        String encodedJson = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
        Cookie cartCookie = new Cookie("cart", encodedJson);
        cartCookie.setMaxAge(86400);
        cartCookie.setPath("/");
        return cartCookie;
    }
}
