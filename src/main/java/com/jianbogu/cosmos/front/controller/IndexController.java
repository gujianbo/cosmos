package com.jianbogu.cosmos.front.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/", "index.html", "index.htm"})
    public String index(Map<String, Object> map){
        return "index";
    }
}
