package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/error")
    public String getError() {
        return "Error en la app";
    }

    // Buscar por correo (útil para perfil)
    @GetMapping("/getCorreo")
    public ResponseEntity<List<Usuario>> getUserMail(@RequestParam String correo) {
        return new ResponseEntity<>(usuarioService.getUsuarioCorreo(correo), HttpStatus.OK);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Eliminar usuario por ID
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Actualizar correo o teléfono
    @PutMapping("/contacto/{id}")
    public ResponseEntity<Usuario> updateUsuarioContacto(
            @PathVariable int id,
            @RequestParam(required = false) String nuevoCorreo,
            @RequestParam(required = false) String nuevoTelefono) {

        Usuario usuario = usuarioService.updateUsuarioContacto(id, nuevoCorreo, nuevoTelefono);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    // Actualizar monedas (solo admin debería tener acceso)
    @PutMapping("/updateMoneda/{id}")
    public ResponseEntity<Usuario> updateMonedas(@PathVariable int id, @RequestParam int nuevaMoneda) {
        try {
            Usuario usuario = usuarioService.updateMonedas(id, nuevaMoneda);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Listar todos los usuarios (solo ADMIN debería acceder)
    @GetMapping("/getTodos")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

}
