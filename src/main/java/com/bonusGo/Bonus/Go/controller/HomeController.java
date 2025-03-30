package com.bonusGo.Bonus.Go.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Esta es una página pública. No requiere autenticación.";
    }

    @GetMapping("/home")
    public String privateEndpoint() {
        return "Bienvenido a la zona privada. ¡Has iniciado sesión!";
    }
}

