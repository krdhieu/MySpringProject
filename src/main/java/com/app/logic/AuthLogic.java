package com.app.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.DispatcherServlet;

@Component
public class AuthLogic {
    @Autowired
    AccountLogic accountLogic;

}
