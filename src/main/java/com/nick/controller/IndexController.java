package com.nick.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: WZL
 * @Date: 2019/12/18 23:18
 */
@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}
}
