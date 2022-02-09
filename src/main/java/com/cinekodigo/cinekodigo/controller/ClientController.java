package com.cinekodigo.cinekodigo.controller;

import com.cinekodigo.cinekodigo.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
    private ClientService clientService;

    public ClientController(ClientService clientService) {
        super();
        this.clientService = clientService;
    }

    //handle method
    @GetMapping("/clients")
    public String listClients(Model model){
        model.addAttribute("clients",clientService.getAllClients());
        return "clients";
        

    }

}
