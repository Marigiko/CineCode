package com.cinekodigo.cinekodigo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PathController {

    //handle method
    @GetMapping("/index")
    public String index(Model model){

        return "home";

    }

    @GetMapping("/tickets")
    public String tickets(Model model){

        return "cart";

    }

    @GetMapping("/administration")
    public String aministration(Model model){

        return "clients";

    }

}
