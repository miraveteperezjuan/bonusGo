package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.dto.LoginDTO;
import com.bonusGo.Bonus.Go.dto.UsuarioDTO;
import com.bonusGo.Bonus.Go.payload.LoginMesage;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    //http://localhost:8080/usuario/

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody UsuarioDTO usuarioDTO)
    {
        usuarioService.add(usuarioDTO);
        return "Usuario agregado correctamente con nombre: " + usuarioDTO.getNombre();
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO)
    {
        LoginMesage loginMesage = usuarioService.loginUsuario(loginDTO);
        return ResponseEntity.ok(loginMesage);
    }

}
