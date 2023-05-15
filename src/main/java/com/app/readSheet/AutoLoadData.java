package com.app.readSheet;

import com.app.entity.*;
import com.app.logic.*;
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

//        CustomerLogic customerLogic = context.getBean(CustomerLogic.class);
//        readSheet.withEntity(customerLogic);
//        readSheet.readRecord("./src/main/resources/customers.xlsx", Customer.class, 1);
//
//        RoleLogic roleLogic = context.getBean(RoleLogic.class);
//        readSheet.withEntity(roleLogic);
//        readSheet.readRecord("./src/main/resources/roles.xlsx", Role.class, 1);
//
//        PermissionLogic permissionLogic = context.getBean(PermissionLogic.class);
//        readSheet.withEntity(permissionLogic);
//        readSheet.readRecord("./src/main/resources/permissions.xlsx", Permission.class, 1);
//
//        ProductTypeLogic productTypeLogic = context.getBean(ProductTypeLogic.class);
//        readSheet.withEntity(productTypeLogic);
//        readSheet.readRecord("./src/main/resources/producttypes.xlsx", ProductType.class, 1);

        ProductLogic productLogic = context.getBean(ProductLogic.class);
        readSheet.withEntity(productLogic);
        readSheet.readRecord("./src/main/resources/products.xlsx", Product.class, 1);
    }
}
