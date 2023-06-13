package com.app.readSheet;

import com.app.entity.*;
import com.app.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AutoLoadData {
    @Autowired
    ApplicationContext context;
    @PostConstruct
    public void Load() {
        ReadSheet readSheet = context.getBean(ReadSheet.class);

        CustomerLogic customerLogic = context.getBean(CustomerLogic.class);
        readSheet.withEntity(customerLogic);
        readSheet.readRecord("rawData/customers.xlsx", Customer.class, 1);

        RoleLogic roleLogic = context.getBean(RoleLogic.class);
        readSheet.withEntity(roleLogic);
        readSheet.readRecord("rawData/roles.xlsx", Role.class, 1);

        PermissionLogic permissionLogic = context.getBean(PermissionLogic.class);
        readSheet.withEntity(permissionLogic);
        readSheet.readRecord("rawData/permissions.xlsx", Permission.class, 1);

        ProductTypeLogic productTypeLogic = context.getBean(ProductTypeLogic.class);
        readSheet.withEntity(productTypeLogic);
        readSheet.readRecord("rawData/product-types.xlsx", ProductType.class, 1);

        ProductLogic productLogic = context.getBean(ProductLogic.class);
        readSheet.withEntity(productLogic);
        readSheet.readRecord("rawData/products.xlsx", Product.class, 1);

        OrderStatusLogic orderStatusLogic = context.getBean(OrderStatusLogic.class);
        readSheet.withEntity(orderStatusLogic);
        readSheet.readRecord("rawData/order-statuses.xlsx", OrderStatus.class, 1);

        AccountLogic accountLogic = context.getBean(AccountLogic.class);
        readSheet.withEntity(accountLogic);
        readSheet.readRecord("rawData/accounts.xlsx", Account.class, 1);

        AccountRolePermissionLogic accountRolePermissionLogic = context.getBean(AccountRolePermissionLogic.class);
        readSheet.withEntity(accountRolePermissionLogic);
        readSheet.readRecord("rawData/account-role-permission.xlsx", AccountRolePermission.class, 1);

        CustomerOrderLogic customerOrderLogic = context.getBean(CustomerOrderLogic.class);
        readSheet.withEntity(customerOrderLogic);
        readSheet.readRecord("rawData/orders.xlsx", CustomerOrder.class, 1);

        OrderDetailsLogic orderDetailLogic = context.getBean(OrderDetailsLogic.class);
        readSheet.withEntity(orderDetailLogic);
        readSheet.readRecord("rawData/order-details.xlsx", OrderDetails.class, 1);
    }
}
