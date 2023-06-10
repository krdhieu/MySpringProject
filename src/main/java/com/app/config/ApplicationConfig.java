package com.app.config;

import com.app.config.security.MyUserDetails;
import com.app.logic.AccountLogic;
import com.app.logic.AccountRolePermissionLogic;
import com.app.logic.CustomerLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ComponentScan("com.app")
public class ApplicationConfig {
    @Value("${app.url}")
    private String appUrl;
    @Value("${app.avatarDir}")
    private String avatarDir;

    @Value("${app.productDir}")
    private String productDir;

    @Bean
    public String appUrl() {
        return appUrl;
    }

    @Bean
    public String avatarDir() {
        return avatarDir;
    }

    @Bean String productDir() {
        return productDir;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Autowired
            AccountLogic accountLogic;
            @Autowired
            CustomerLogic customerLogic;
            @Autowired
            AccountRolePermissionLogic accountRolePermissionLogic;

            @Override
            public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                MyUserDetails myUserDetails = new MyUserDetails()
                        .setAccountRolePermissionLogic(accountRolePermissionLogic)
                        .setAccount(accountLogic.findAccountByUsername(username))
                        .setCustomer(customerLogic.findCustomerByUsername(username));
                return myUserDetails;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
