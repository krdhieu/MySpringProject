package com.app.auth;

import com.app.config.security.MyUserDetails;
import com.app.config.security.jwt.JwtService;
import com.app.entity.Account;
import com.app.logic.AccountLogic;
import com.app.logic.AccountRolePermissionLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

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
    UserDetailsService userDetailsService;

    public AuthResponse responseToken(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        Account account = accountLogic.findAccountByUsername(authRequest.getUsername());
        MyUserDetails myUserDetails = (MyUserDetails) userDetailsService.loadUserByUsername(account.getUsername());
        String jwt = jwtService.generateToken(myUserDetails.setAccount(account));
        StringBuilder role_permission = new StringBuilder();
        for (GrantedAuthority authority : myUserDetails.getAuthorities()) {
            role_permission.append(authority.getAuthority()).append("; ");
        }
        System.out.println(role_permission);
        return new AuthResponse().withToken(jwt);
    }
}
