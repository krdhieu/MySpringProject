package com.app.auth;

import com.app.entity.Account;
import com.app.auth.AuthResponse;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        return new ResponseEntity<>(authService.responseToken(authRequest), HttpStatus.ACCEPTED);
    }
}
