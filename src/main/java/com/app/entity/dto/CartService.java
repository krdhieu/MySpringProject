package com.app.entity.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class CartService {
    @Autowired
    CartLogic cartLogic;

    public Cart addProductToCart(HttpServletRequest request, long productId, int quantity) {
        return cartLogic.addProductToCart(request ,productId, quantity);
    }
}
