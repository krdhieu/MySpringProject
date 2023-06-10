package com.app.auth;


import com.app.entity.dto.Cart;
import com.app.entity.dto.CartLogic;
import com.app.entity.dto.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    CartService cartService;

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        if (authRequest == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        response.addCookie(cartService.insertNewCartIntoCookie());
        response.addCookie(authService.jwtCookie(authRequest));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public @ResponseBody ResponseEntity<?> logout(HttpServletResponse response) {
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName("cart");
        cookieGenerator.setCookiePath("/");
        cookieGenerator.setCookieMaxAge(0);
        cookieGenerator.addCookie(response, "");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
