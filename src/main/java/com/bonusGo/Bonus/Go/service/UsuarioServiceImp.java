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
    private TransaccionRepository transaccionRepository;

    @Autowired
    private GananciaMonedasRepository gananciasRepository;

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
    public Usuario registerAdministrador(Usuario usuario) {
        usuario.setMoneda(0);

        Rol rol = rolRepository.findById(2).orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        usuario.setRol(rol);

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getUsuarioCorreo(String correo) {
        List<Usuario> usuarios = usuarioRepository.getByCorreo(correo);

        // Se verifica si la moneda es nula, en ese caso, se le asigna 0
        for (Usuario usuario : usuarios) {
            if (usuario.getMoneda() == null) {
                usuario.setMoneda(0);
            }
        }
        return usuarios;
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
    public Usuario updateUsuarioContacto(int id, String nuevoCorreo, String nuevoTelefono) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (nuevoCorreo != null && !nuevoCorreo.isEmpty()) {
            if (usuarioRepository.existsByCorreo(nuevoCorreo)) {
                throw new RuntimeException("El correo ya está en uso");
            }
            usuario.setCorreo(nuevoCorreo);
        }

        if (nuevoTelefono != null && !nuevoTelefono.isEmpty()) {
            usuario.setTelefono(nuevoTelefono);
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario updateMonedas(int id, int nuevaMoneda) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setMoneda(nuevaMoneda);
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
    public Integer getMonedasById(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getMoneda();
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByCorreo(email);
    }
}
