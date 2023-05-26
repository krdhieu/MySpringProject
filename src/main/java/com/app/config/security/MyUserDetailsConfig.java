package com.app.config.security;

import com.app.logic.AccountLogic;
import com.app.logic.AccountRolePermissionLogic;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyUserDetailsConfig {
    @Autowired
    AccountRolePermissionLogic accountRolePermissionLogic;

    @Bean
    public MyUserDetails myUserDetails() {
        return new MyUserDetails().setAccountRolePermissionLogic(accountRolePermissionLogic);
    }
}
