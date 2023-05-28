package com.app.entity.dto;

import java.util.HashMap;

public class Cart {
    HashMap<Long, Integer> cartItems = new HashMap<>();

    public Cart() {

    }

    public HashMap<Long, Integer> getCartItems() {
        return cartItems;
    }

    public void setCartItems(HashMap<Long, Integer> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                '}';
    }

}
