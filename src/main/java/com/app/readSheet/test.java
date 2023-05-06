package com.app.readSheet;

import com.app.entity.Customer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test {
    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, ClassNotFoundException {
        Field field = Customer.class.getDeclaredField("id");
        System.out.println(field.getType());

    }
}
