package com.portfolio.ohousev1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String home() {
        log.info("home controller");
        return "main";
    }
    @RequestMapping("/login")
    public String login() {
        log.info("home controller");
        return "main";
    }
    @GetMapping("/example")
    public void getTest(){
        log.info("테스트 컨트롤러");
    }
}
