package com.app.readSheet;

import com.app.entity.*;
import com.app.logic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Component
public class AutoLoadData {
    @Autowired
    ApplicationContext context;

    @PostConstruct
    public void Load() throws FileNotFoundException {
        ReadSheet readSheet = context.getBean(ReadSheet.class);
        try {
            CustomerLogic customerLogic = context.getBean(CustomerLogic.class);
            readSheet.withEntity(customerLogic);
            readSheet.readRecord(this.absoluteFilePath("rawData/customer.xlsx"), Customer.class, 1);

            RoleLogic roleLogic = context.getBean(RoleLogic.class);
            readSheet.withEntity(roleLogic);
            readSheet.readRecord(this.absoluteFilePath( "rawData/roles.xlsx"), Role.class, 1);

            PermissionLogic permissionLogic = context.getBean(PermissionLogic.class);
            readSheet.withEntity(permissionLogic);
            readSheet.readRecord(this.absoluteFilePath("rawData/permissions.xlsx"), Permission.class, 1);

            ProductTypeLogic productTypeLogic = context.getBean(ProductTypeLogic.class);
            readSheet.withEntity(productTypeLogic);
            readSheet.readRecord(this.absoluteFilePath("rawData/product-types.xlsx"), ProductType.class, 1);

            ProductLogic productLogic = context.getBean(ProductLogic.class);
            readSheet.withEntity(productLogic);
            readSheet.readRecord(this.absoluteFilePath ("rawData/products.xlsx"), Product.class, 1);

            OrderStatusLogic orderStatusLogic = context.getBean(OrderStatusLogic.class);
            readSheet.withEntity(orderStatusLogic);
            readSheet.readRecord(this.absoluteFilePath("rawData/order-statuses.xlsx"), OrderStatus.class, 1);

            AccountLogic accountLogic = context.getBean(AccountLogic.class);
            readSheet.withEntity(accountLogic);
            readSheet.readRecord( this.absoluteFilePath( "rawData/accounts.xlsx"), Account.class, 1);

            AccountRolePermissionLogic accountRolePermissionLogic = context.getBean(AccountRolePermissionLogic.class);
            readSheet.withEntity(accountRolePermissionLogic);
            readSheet.readRecord( this.absoluteFilePath( "rawData/account-role-permission.xlsx"), AccountRolePermission.class, 1);

            CustomerOrderLogic customerOrderLogic = context.getBean(CustomerOrderLogic.class);
            readSheet.withEntity(customerOrderLogic);
            readSheet.readRecord(this.absoluteFilePath( "rawData/orders.xlsx"), CustomerOrder.class, 1);

            OrderDetailsLogic orderDetailLogic = context.getBean(OrderDetailsLogic.class);
            readSheet.withEntity(orderDetailLogic);
            readSheet.readRecord(this.absoluteFilePath( "rawData/order-details.xlsx"), OrderDetails.class, 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String absoluteFilePath(String relativePath) throws URISyntaxException {
        URL res = getClass().getClassLoader().getResource(relativePath);
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }
}