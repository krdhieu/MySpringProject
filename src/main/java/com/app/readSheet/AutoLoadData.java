package com.app.readSheet;

import com.app.entity.Customer;
import com.app.entity.Role;
import com.app.logic.CustomerLogic;
import com.app.logic.EntityLogic;
import com.app.logic.RoleLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

//@Component
public class AutoLoadData {
    @Autowired
    ApplicationContext context;
    @PostConstruct
    public void Load() {
        ReadSheet readSheet = context.getBean(ReadSheet.class);
        CustomerLogic customerLogic = context.getBean(CustomerLogic.class);
        readSheet.withEntity(customerLogic);
        readSheet.readRecord("./src/main/resources/customer.xlsx", Customer.class, 1);
        RoleLogic roleLogic = context.getBean(RoleLogic.class);
        readSheet.withEntity(roleLogic);
        readSheet.readRecord("./src/main/resources/role.xlsx", Role.class, 1);
    }
}
