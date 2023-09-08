package com.portfolio.ohousev1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "fragments/main";
    }

    @GetMapping("/login")
    public String login() {
        log.info("login controller");
        return "fragments/login_form";
    }



}
