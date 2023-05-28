package com.app.http;

import com.app.entity.dto.Cart;
import com.app.entity.dto.CartLogic;
import com.app.entity.dto.CartService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
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


        return new ResponseEntity<>(cartService.addProductToCart(request, productId, quantity), HttpStatus.OK);
    }

}
