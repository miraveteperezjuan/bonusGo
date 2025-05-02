package com.bonusGo.Bonus.Go.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    //LOGICA QUE VA A PODER EL ADMIN

    @GetMapping("/index")
    public ResponseEntity<String> index() {
        return ResponseEntity.ok("Bienvenido, ADMIN. Esta es tu zona privada.");
    }

    @DeleteMapping("/eliminarUsuario/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        return ResponseEntity.ok("Usuario eliminado por ADMIN.");
    }


}
