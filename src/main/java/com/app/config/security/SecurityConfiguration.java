package com.app.config.security;


import com.app.config.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.Access;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    JwtAuthenticationFilter jwtAuthFilter;
    @Autowired
    AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/api/v1/auth/**",
                        "/api/v1/cart/**",
                        "/v3/api-docs",
                        "/v2/api-docs",
                        "/swagger-ui/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html#/**"
                )
                .permitAll()
                // API Product
                .antMatchers("/api/v1/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-name/{name}").hasAuthority("VIEW_PRODUCT")
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-product-type/{typeId}").hasAuthority("VIEW_PRODUCT")
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-id/{id}").hasAuthority("VIEW_PRODUCT")
                .antMatchers(HttpMethod.GET, "/api/v1/product/all").hasAuthority("VIEW_PRODUCT")
                .antMatchers(HttpMethod.POST, "/api/v1/product/create-product").hasAuthority("CREATE_PRODUCT")
                .antMatchers(HttpMethod.PUT, "/api/v1/product/update-product").hasAuthority("UPDATE_PRODUCT")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product/delete-by-id/{id}").hasAuthority("DELETE_PRODUCT")

                // API Product Type
                .antMatchers("/api/v1/product-type/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/find-by-name/{name}").hasAuthority("VIEW_PRODUCT_TYPE")
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/find-by-id/{id}").hasAuthority("VIEW_PRODUCT_TYPE")
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/all").hasAuthority("VIEW_PRODUCT_TYPE")
                .antMatchers(HttpMethod.POST, "/api/v1/product-type/create-product-type").hasAuthority("CREATE_PRODUCT_TYPE")
                .antMatchers(HttpMethod.PUT, "/api/v1/product-type/update-product-type").hasAuthority("UPDATE_PRODUCT_TYPE")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product-type/delete-by-id/{id}").hasAuthority("DELETE_PRODUCT_TYPE")

                // API Order Status
                .antMatchers("/api/v1/order-status/**").hasAuthority("VIEW_ORDER_STATUS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/all").hasAuthority("VIEW_ORDER_STATUS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/find-by-id/{id}").hasAuthority("VIEW_ORDER_STATUS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/find-by-name/{name}").hasAuthority("VIEW_ORDER_STATUS")
                .antMatchers(HttpMethod.POST, "/api/v1/order-status/create-order-status").hasAuthority("CREATE_ORDER_STATUS")
                .antMatchers(HttpMethod.PUT, "/api/v1/order-status/update-order-status").hasAuthority("UPDATE_ORDER_STATUS")
                .antMatchers(HttpMethod.DELETE, "/api/v1/order-status/delete-by-id/{id}").hasAuthority("DELETE_ORDER_STATUS")

                // API Order Detail
                .antMatchers("/api/v1/order-detail/**").hasAuthority("VIEW_ORDER_DETAILS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-detail-by-id/{id}").hasAuthority("VIEW_ORDER_DETAILS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-by-order-id/{orderId}").hasAuthority("VIEW_ORDER_DETAILS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-by-customer-id/{customerId}").hasAuthority("VIEW_ORDER_DETAILS")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/all").hasAuthority("VIEW_ORDER_DETAILS")
                .antMatchers(HttpMethod.PUT, "/api/v1/order-detail/update-order-detail").hasAuthority("UPDATE_ORDER_DETAILS")
                .antMatchers(HttpMethod.DELETE, "/api/v1/order-detail/delete-by-id/{id}").hasAuthority("DELETE_ORDER_DETAILS")

                // API Customer
                .antMatchers("/api/v1/customer/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/customer/update-customer").hasAuthority("UPDATE_CUSTOMER")
                .antMatchers(HttpMethod.POST, "/api/v1/customer/create-customer").hasAuthority("CREATE_CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/find-by-name/{name}").hasAuthority("VIEW_CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/find-by-id/{id}").hasAuthority("VIEW_CUSTOMER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/all").hasAuthority("VIEW_CUSTOMER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/customer/delete-by-id/{id}").hasAuthority("DELETE_CUSTOMER")

                // API Customer Order
                .antMatchers("/api/v1/customer-order/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/all").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-id/{id}").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-order-date/{date}").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-customer-id/{customerId}").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-status-id/{statusId}").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.POST, "/api/v1/customer-order/find-by-customer-and-created-date").hasAuthority("VIEW_ORDER")
                .antMatchers(HttpMethod.POST, "/api/v1/customer-order/create-customer-order").hasAuthority("CREATE_ORDER")
                .antMatchers(HttpMethod.PUT, "/api/v1/customer-order/update-customer-order").hasAuthority("UPDATE_ORDER")
                .antMatchers(HttpMethod.DELETE, "/api/v1/customer-order/delete-by-id/{id}").hasAuthority("DELETE_ORDER")

                // API Account
                .antMatchers("/api/v1/account/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account/all").hasAuthority("VIEW_ACCOUNT")
                .antMatchers(HttpMethod.GET, "/api/v1/account/find-by-username/{username}").hasAuthority("VIEW_ACCOUNT")
                .antMatchers(HttpMethod.GET, "/api/v1/account/find-by-id/{id}").hasAuthority("VIEW_ACCOUNT")
                .antMatchers(HttpMethod.POST, "/api/v1/account/create-account").hasAuthority("CREATE_ACCOUNT")
                .antMatchers(HttpMethod.PUT, "/api/v1/account/update-account").hasAuthority("UPDATE_ACCOUNT")
                .antMatchers(HttpMethod.DELETE, "/api/v1/account/delete-by-id/{id}").hasAuthority("DELETE_ACCOUNT")

                // API Role
                .antMatchers("/api/v1/role/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/role/all").hasAuthority("VIEW_ROLE")
                .antMatchers(HttpMethod.GET, "/api/v1/role/find-by-name/{name}").hasAuthority("VIEW_ROLE")
                .antMatchers(HttpMethod.GET, "/api/v1/role/find-by-id/{id}").hasAuthority("VIEW_ROLE")

                // API Authentication
                .antMatchers("/api/v1/auth/login").permitAll()

                // API Account Role Permission
                .antMatchers("/api/v1/account-role-permission/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/all").hasAuthority("VIEW_ACCOUNT_ROLE_PERMISSION")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-role-permission-name-by-account-id/{accountId}").hasAuthority("VIEW_ACCOUNT_ROLE_PERMISSION")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-by-id/{id}").hasAuthority("VIEW_ACCOUNT_ROLE_PERMISSION")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-by-account-id/{accountId}").hasAuthority("VIEW_ACCOUNT_ROLE_PERMISSION")
                .antMatchers(HttpMethod.POST, "/api/v1/account-role-permission/create-account-role-permission").hasAuthority("CREATE_ACCOUNT_ROLE_PERMISSION")
                .antMatchers(HttpMethod.DELETE, "/api/v1/account-role-permission/delete-account-role-permission-by-id/{id}").hasAuthority("DELETE_ACCOUNT_ROLE_PERMISSION")

                // API Permission
                .antMatchers("/api/v1/permission/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/permission/all").hasAuthority("VIEW_PERMISSION")
                .antMatchers(HttpMethod.GET, "/api/v1/permission/find-by-name/{name}").hasAuthority("VIEW_PERMISSION")
                .antMatchers(HttpMethod.GET, "/api/v1/permission/find-by-id/{id}").hasAuthority("VIEW_PERMISSION")

                //API Cart
//                .antMatchers("/api/v1/cart/**").hasAuthority("ADMIN")
//                //test
//                .antMatchers("/api/v1/cart/add-product/{productId}/{quantity}").hasAuthority("ADMIN")


                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
