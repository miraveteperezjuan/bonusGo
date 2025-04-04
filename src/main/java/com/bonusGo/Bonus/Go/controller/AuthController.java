package com.bonusGo.Bonus.Go.controller;

import com.bonusGo.Bonus.Go.model.RegisterRequest;
import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.model.UsuarioLoginRequest;
import com.bonusGo.Bonus.Go.payload.LoginResponse;
import com.bonusGo.Bonus.Go.repository.RolRepository;
import com.bonusGo.Bonus.Go.security.JwtUtil;
import com.bonusGo.Bonus.Go.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UsuarioService userService;
    private final RolRepository rolRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(JwtUtil jwtUtil, UsuarioService userService, RolRepository rolRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.rolRepository = rolRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody UsuarioLoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getCorreo(),
                            loginRequest.getPass()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtUtil.createToken(auth);
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al logear - " + e.getMessage());
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {

        if (userService.existsByEmail(registerRequest.getCorreo())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: Email en uso.");
        }

        Usuario usuario = new Usuario(registerRequest.getNombre(),
                registerRequest.getApellido(),
                registerRequest.getCorreo(),
                registerRequest.getTelefono(),
                passwordEncoder.encode(registerRequest.getPassword()));

        usuario.setMoneda(0);
        Rol rol = rolRepository.findById(1).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        try {
            userService.registerUsuario(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado con exito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}