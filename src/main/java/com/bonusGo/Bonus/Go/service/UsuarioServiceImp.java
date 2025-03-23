package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.dto.LoginDTO;
import com.bonusGo.Bonus.Go.dto.UsuarioDTO;
import com.bonusGo.Bonus.Go.model.Rol;
import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.payload.LoginMesage;
import com.bonusGo.Bonus.Go.repository.RolRepository;
import com.bonusGo.Bonus.Go.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public String add(UsuarioDTO usuarioDTO) {

        Rol rolInicial = rolRepository.getRol(1);

        Usuario usuario = new Usuario(
                usuarioDTO.getId_Usuario(),
                usuarioDTO.getNombre(),
                usuarioDTO.getApellido(),
                usuarioDTO.getCorreo(),
                usuarioDTO.getTelefono(),
                this.passwordEncoder.encode(usuarioDTO.getPassword()),
                rolInicial);

                usuarioRepository.save(usuario);
        return usuario.getNombre();
    }

    @Override
    public LoginMesage loginUsuario(LoginDTO loginDTO) {
        String msg = "";

        Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
        if (usuario != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = usuario.getPassword();
            Boolean passOk = passwordEncoder.matches(password, encodedPassword);
            if (passOk) {
                Optional<Usuario> employee = usuarioRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginMesage("Registrado con éxito", true);
                } else {
                    return new LoginMesage("Fallo en cargar", false);
                }
            } else {
                return new LoginMesage("Contraseña no encontrada", false);
            }
        }else {
            return new LoginMesage("Este email no exíste", false);
        }
    }
}
