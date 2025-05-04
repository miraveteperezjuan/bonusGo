package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.payload.LoginResponse;
import com.bonusGo.Bonus.Go.repository.GananciaMonedasRepository;
import com.bonusGo.Bonus.Go.repository.RolRepository;
import com.bonusGo.Bonus.Go.repository.TransaccionRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Usuario getlogin(String correo, String pass) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCorreo(correo);

        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();

            if (passwordEncoder.matches(pass, usuario.getPassword())) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public Usuario registerUsuario(Usuario usuario) {
        usuario.setMoneda(0);

        Rol rol = rolRepository.findById(1).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void deleteUsuario(int id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (!usuarioOptional.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }
        Usuario usuario = usuarioOptional.get();
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario updateMonedas(int id, int monedas) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int nuevasMonedas = usuario.getMoneda() + monedas;

        usuario.setMoneda(nuevasMonedas);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateUsuario(int id, Usuario usuarioActualizado) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        existente.setNombre(usuarioActualizado.getNombre());
        existente.setApellido(usuarioActualizado.getApellido());
        existente.setCorreo(usuarioActualizado.getCorreo());
        existente.setTelefono(usuarioActualizado.getTelefono());
        existente.setRol(usuarioActualizado.getRol());
        existente.setMoneda(usuarioActualizado.getMoneda());

        return usuarioRepository.save(existente);
    }

    @Override
    public Usuario updateUsuarioPerfil(int id, Usuario usuarioActualizado) {
        Usuario existente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        existente.setNombre(usuarioActualizado.getNombre());
        existente.setApellido(usuarioActualizado.getApellido());
        existente.setCorreo(usuarioActualizado.getCorreo());
        existente.setTelefono(usuarioActualizado.getTelefono());

        return usuarioRepository.save(existente);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByCorreo(email);
    }
}
