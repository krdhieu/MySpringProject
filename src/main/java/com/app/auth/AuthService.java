package com.app.auth;

import com.app.config.security.MyUserDetails;
import com.app.config.security.jwt.JwtService;
import com.app.entity.Account;
import com.app.logic.AccountLogic;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JwtService jwtService;

    @Autowired
    AccountRolePermissionLogic accountRolePermissionLogic;

    @Autowired
    AccountLogic accountLogic;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MyUserDetails myUserDetails;

    public AuthResponse responseToken(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        Account account = accountLogic.findAccountByUsername(authRequest.getUsername());
        String jwt = jwtService.generateToken(myUserDetails.setAccount(account));
        return new AuthResponse().withToken(jwt);
    }
}
