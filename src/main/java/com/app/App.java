package com.app;

import com.app.entity.Customer;
import com.app.entity.CustomerOrder;
import com.app.entity.Role;
import com.app.logic.CustomerLogic;
import com.app.logic.EntityLogic;
import com.app.logic.RoleLogic;
import com.app.readSheet.ReadSheet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        ApplicationContext context = SpringApplication.run(App.class, args);

    }
}
