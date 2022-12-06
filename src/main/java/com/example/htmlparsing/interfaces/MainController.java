package com.example.htmlparsing.interfaces;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping
    public String home(){
        return "main";
    }

    @GetMapping("/tag")
    public String deleteTag(){
        return "tagRemove";
    }
    @GetMapping("/text")
    public String deleteText(){
        return "deleteText";
    }
}
