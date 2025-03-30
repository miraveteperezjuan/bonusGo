package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.model.UsuarioLoginRequest;
import com.bonusGo.Bonus.Go.payload.LoginResponse;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/usuario/

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    //http://localhost:8080/usuario/error
    @GetMapping("/error")
    public String getError(){
        return "Error en la app";
    }

    //http://localhost:8080/usuario/getCorreo
    @GetMapping("/getCorreo")
    public ResponseEntity<List<Usuario>> getUserMail(@RequestParam String correo){
        return new ResponseEntity<>(usuarioService.getUsuarioCorreo(correo), HttpStatus.OK);
    }

    @GetMapping("getTodos")
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        return new ResponseEntity<>(usuarioService.getAllUsuarios(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //http://localhost:8080/usuario/login
    @GetMapping("/login")
    public ResponseEntity<Usuario> getUserLogin(@RequestParam String correo, @RequestParam String pass ){

        Usuario usuario = usuarioService.getlogin(correo,pass);

        if (usuario!=null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> getUserLogin(@RequestBody UsuarioLoginRequest loginRequest){

        Usuario usuario = usuarioService.getlogin(loginRequest.getCorreo(), loginRequest.getPass());

        if (usuario != null){
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/registroUser")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registerUsuario(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<Usuario> registerAdmin(@RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registerAdministrador(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable int id) {
        try {
            usuarioService.deleteUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/contacto/{id}")
    public ResponseEntity<Usuario> updateUsuarioContacto(@PathVariable int id, @RequestParam(required = false) String nuevoCorreo,
        @RequestParam(required = false) String nuevoTelefono) {
        Usuario usuario = usuarioService.updateUsuarioContacto(id, nuevoCorreo, nuevoTelefono);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/updateMoneda/{id}")
    public ResponseEntity<Usuario> updateMonedas(@PathVariable int id, @RequestParam int nuevaMoneda) {
        try {
            Usuario usuario = usuarioService.updateMonedas(id, nuevaMoneda);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

/*@GetMapping("/login")
    public ResponseEntity<?> getUserLogin(@RequestParam String correo, @RequestParam String pass) {
        // Validar que los parámetros no sean nulos o vacíos
        if (correo == null || correo.isEmpty() || pass == null || pass.isEmpty()) {
            return new ResponseEntity<>("Correo o contraseña no proporcionados", HttpStatus.BAD_REQUEST);
        }

        Usuario usuario = usuarioService.getlogin(correo, pass);

        if (usuario != null) {
            // Generar el token JWT
            String token = jwtService.generateToken(usuario);

            // Responder con el token y datos del usuario
            return ResponseEntity.ok(new LoginResponse(token, usuario));
        }

        return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
    }*/
