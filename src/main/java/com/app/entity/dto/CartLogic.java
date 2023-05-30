package com.app.entity.dto;

import com.app.entity.Product;
import com.app.logic.ProductLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.google.gson.Gson;

@Component
public class CartLogic {
    @Autowired
    ProductLogic productLogic;

    public Cart decodeCartFromEncodedJson(String encodedJson)  {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedJson);
            String decodedJson = new String(decodedBytes, StandardCharsets.UTF_8);
            Cart cart = new Gson().fromJson(decodedJson, Cart.class);
            return cart;
        } catch (Exception e) {
            return new Cart();
        }
    }

    public Cart addProductToCart(Cart currentCart, long productId, int quantity) {
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

    public Cart deleteProductInCart(Long productId, Cart currentCart) {
        if(currentCart.cartItems.containsKey(productId)) {
            currentCart.cartItems.remove(productId);
        }
        return currentCart;
    }

    public Cookie saveCartIntoCookie(Cart cart) {
        String json = new Gson().toJson(cart);
        String encodedJson = Base64.getEncoder().encodeToString(json.getBytes(StandardCharsets.UTF_8));
        Cookie cartCookie = new Cookie("cart", encodedJson);
        cartCookie.setMaxAge(86400);
        cartCookie.setPath("/");
        return cartCookie;
    }
}
