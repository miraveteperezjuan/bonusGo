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
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    @PostMapping("/loginUser")
    public ResponseEntity<?> loginUser(@RequestBody UsuarioLoginRequest loginRequest) {
        Usuario usuario = userService.getlogin(loginRequest.getCorreo(), loginRequest.getPass());

        if (usuario == null || !usuario.getRol().getNombre().equals("ROLE_USER")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso denegado");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getCorreo(),
                        loginRequest.getPass()
                )
        );

        String token = jwtUtil.createToken(auth);
        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> loginAdmin(@RequestBody UsuarioLoginRequest loginRequest) {
        Usuario usuario = userService.getlogin(loginRequest.getCorreo(), loginRequest.getPass());

        if (usuario == null || !usuario.getRol().getNombre().equals("ROLE_ADMIN")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso denegado");
        }

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getCorreo(),
                        loginRequest.getPass()
                )
        );

        String token = jwtUtil.createToken(auth);
        return ResponseEntity.ok(new LoginResponse(token));
    }


    @PostMapping("/registrar")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userService.existsByEmail(request.getCorreo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ya en uso.");
        }

        Usuario usuario = new Usuario(
                request.getNombre(),
                request.getApellido(),
                request.getCorreo(),
                request.getTelefono(),
                passwordEncoder.encode(request.getPassword())
        );

        usuario.setMoneda(0);
        Rol rolUser = rolRepository.findByNombre("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
        usuario.setRol(rolUser);

        userService.registerUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado.");
    }

    @PostMapping("/registrarAdmin")
    public ResponseEntity<?> registrarAdmin(@RequestBody RegisterRequest registerRequest) {
        if (userService.existsByEmail(registerRequest.getCorreo())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya está en uso.");
        }

        Usuario admin = new Usuario(
                registerRequest.getNombre(),
                registerRequest.getApellido(),
                registerRequest.getCorreo(),
                registerRequest.getTelefono(),
                passwordEncoder.encode(registerRequest.getPassword())
        );

        admin.setMoneda(0);

        Rol rolAdmin = rolRepository.findByNombre("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Rol ADMIN no encontrado"));

        admin.setRol(rolAdmin);

        userService.registerAdministrador(admin);

        return ResponseEntity.status(HttpStatus.CREATED).body("Administrador registrado con éxito.");
    }

    @GetMapping("/token/info")
    public ResponseEntity<?> getTokenInfo(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Token no proporcionado o mal formado"));
        }

        String token = authHeader.substring(7); // Quita el prefijo "Bearer "

        if (!jwtUtil.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Token inválido"));
        }

        String correo = jwtUtil.getUsernameFromJwt(token);
        String rol = jwtUtil.getRolFromJwt(token);

        Map<String, String> response = new HashMap<>();
        response.put("correo", correo);
        response.put("rol", rol);

        return ResponseEntity.ok(response);
    }

}

