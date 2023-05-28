package com.app.auth;

import com.app.entity.dto.Cart;
import com.app.entity.dto.CartLogic;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    CartLogic cartLogic;

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        response.addCookie(cartLogic.insertNewCartIntoCookie());
        return new ResponseEntity<>(authService.responseToken(authRequest), HttpStatus.ACCEPTED);
    }
}
