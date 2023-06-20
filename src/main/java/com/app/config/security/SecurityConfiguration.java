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
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/api/v1/account/**",
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
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-name/{name}").hasAnyAuthority("VIEW_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-product-type/{typeId}").hasAnyAuthority("VIEW_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product/find-by-id/{id}").hasAnyAuthority("VIEW_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product/all").hasAnyAuthority("VIEW_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/product/create-product").hasAnyAuthority("CREATE_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/product/update-product").hasAnyAuthority("UPDATE_PRODUCT", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product/delete-by-id/{id}").hasAnyAuthority("DELETE_PRODUCT", "ADMIN")

                // API Product Type
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/find-by-name/{name}").hasAnyAuthority("VIEW_PRODUCT_TYPE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/find-by-id/{id}").hasAnyAuthority("VIEW_PRODUCT_TYPE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/product-type/all").hasAnyAuthority("VIEW_PRODUCT_TYPE")
                .antMatchers(HttpMethod.POST, "/api/v1/product-type/create-product-type").hasAnyAuthority("CREATE_PRODUCT_TYPE", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/product-type/update-product-type").hasAnyAuthority("UPDATE_PRODUCT_TYPE", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/product-type/delete-by-id/{id}").hasAnyAuthority("DELETE_PRODUCT_TYPE", "ADMIN")

                // API Order Status
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/all").hasAnyAuthority("VIEW_ORDER_STATUS", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/find-by-id/{id}").hasAnyAuthority("VIEW_ORDER_STATUS", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/order-status/find-by-name/{name}").hasAnyAuthority("VIEW_ORDER_STATUS", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/order-status/create-order-status").hasAnyAuthority("CREATE_ORDER_STATUS", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/order-status/update-order-status").hasAnyAuthority("UPDATE_ORDER_STATUS", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/order-status/delete-by-id/{id}").hasAnyAuthority("DELETE_ORDER_STATUS", "ADMIN")

                // API Order Detail
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-detail-by-id/{id}").hasAnyAuthority("VIEW_ORDER_DETAILS", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-detail-by-order-id/{orderId}").hasAnyAuthority("VIEW_ORDER_DETAILS", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/find-order-by-customer-id/{customerId}").hasAnyAuthority("VIEW_ORDER_DETAILS", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/order-detail/all").hasAnyAuthority("VIEW_ORDER_DETAILS", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/order-detail/update-order-detail").hasAnyAuthority("UPDATE_ORDER_DETAILS", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/order-detail/delete-by-id/{id}").hasAnyAuthority("DELETE_ORDER_DETAILS", "ADMIN")

                // API Customer
                .antMatchers(HttpMethod.PUT, "/api/v1/customer/update-customer").hasAnyAuthority("UPDATE_CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/customer/create-customer").hasAnyAuthority("CREATE_CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/find-by-name/{name}").hasAnyAuthority("VIEW_CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/find-by-id/{id}").hasAnyAuthority("VIEW_CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer/all").hasAnyAuthority("VIEW_CUSTOMER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/customer/delete-by-id/{id}").hasAnyAuthority("DELETE_CUSTOMER", "ADMIN")

                // API Customer Order
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/all").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-id/{id}").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-order-date/{date}").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/find-by-customer-id-and-or-date/{customerId}/{date}").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/customer-order/find-by-status-id/{statusId}").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/customer-order/find-by-customer-and-created-date").hasAnyAuthority("VIEW_ORDER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/customer-order/create-customer-order").hasAnyAuthority("CREATE_ORDER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/customer-order/update-customer-order").hasAnyAuthority("UPDATE_ORDER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/customer-order/delete-by-id/{id}").hasAnyAuthority("DELETE_ORDER", "ADMIN")

                // API Account
                .antMatchers(HttpMethod.GET, "/api/v1/account/all").hasAnyAuthority("VIEW_ACCOUNT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account/find-by-username/{username}").hasAnyAuthority("VIEW_ACCOUNT", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account/find-by-id/{id}").hasAnyAuthority("VIEW_ACCOUNT", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/account/create-account").hasAnyAuthority("CREATE_ACCOUNT", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/account/update-account").hasAnyAuthority("UPDATE_ACCOUNT", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/account/delete-by-id/{id}").hasAnyAuthority("DELETE_ACCOUNT", "ADMIN")

                // API Role
                .antMatchers(HttpMethod.GET, "/api/v1/role/all").hasAnyAuthority("VIEW_ROLE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/role/find-by-name/{name}").hasAnyAuthority("VIEW_ROLE", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/role/find-by-id/{id}").hasAnyAuthority("VIEW_ROLE", "ADMIN")


                // API Authentication
                .antMatchers("/api/v1/auth/login").permitAll()

                // API Account Role Permission
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/all").hasAnyAuthority("VIEW_ACCOUNT_ROLE_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-role-permission-name-by-account-id/{accountId}").hasAnyAuthority("VIEW_ACCOUNT_ROLE_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-by-id/{id}").hasAnyAuthority("VIEW_ACCOUNT_ROLE_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/account-role-permission/find-by-account-id/{accountId}").hasAnyAuthority("VIEW_ACCOUNT_ROLE_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/account-role-permission/create-account-role-permission").hasAnyAuthority("CREATE_ACCOUNT_ROLE_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/account-role-permission/delete-account-role-permission-by-id/{id}").hasAnyAuthority("DELETE_ACCOUNT_ROLE_PERMISSION", "ADMIN")

                // API Permission
                .antMatchers(HttpMethod.GET, "/api/v1/permission/all").hasAnyAuthority("VIEW_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/permission/find-by-name/{name}").hasAnyAuthority("VIEW_PERMISSION", "ADMIN")
                .antMatchers(HttpMethod.GET, "/api/v1/permission/find-by-id/{id}").hasAnyAuthority("VIEW_PERMISSION", "ADMIN")

                //API Cart
                .antMatchers("/api/v1/cart/add-product/{productId}/{quantity}").hasAnyAuthority("ADMIN", "CUSTOMER")
                .antMatchers("/api/v1/cart/delete-product/{productId}").hasAnyAuthority("ADMIN", "CUSTOMER")

                //API Avatar
                .antMatchers("/api/v1/customer-avatar/**").hasAnyAuthority("CUSTOMER", "ADMIN")
//                .antMatchers(HttpMethod.GET, "/static/uploads/avatar/**").hasAnyAuthority("CUSTOMER", "ADMIN")
                //API Product Image
                .antMatchers("/api/v1/product-image/**").hasAnyAuthority("ADMIN")
//                .antMatchers(HttpMethod.GET, "/static/uploads/product/**").hasAnyAuthority("CUSTOMER", "ADMIN")

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
