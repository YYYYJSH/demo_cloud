package com.yjsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TalkController {

    @GetMapping("/talk")
    public String hello(){
        return "la.html";
    }

    @GetMapping("/js/jquery-3.1.1.js")
    public String jq(){
        return "js/jquery-3.1.1.js";
    }

    @GetMapping("/js/base.js")
    public String base(){
        return "js/base.js";
    }

    @GetMapping("/js/md5.js")
    public String md5(){
        return "js/md5.js";
    }

}
