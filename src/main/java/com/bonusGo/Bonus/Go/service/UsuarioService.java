package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Usuario;
import com.bonusGo.Bonus.Go.payload.LoginResponse;

import java.util.List;

public interface UsuarioService {

    Usuario getlogin(String correo, String pass);

    Usuario registerUsuario(Usuario usuario);

    Usuario registerAdministrador(Usuario usuario);

    List<Usuario> getUsuarioCorreo(String correo);

    List<Usuario> getAllUsuarios();

    Usuario getUsuarioById(int id);

    void deleteUsuario(int id);

    Usuario updateUsuarioContacto(int id, String nuevoCorreo, String nuevoTelefono);

    Usuario updateMonedas(int id, int monedas);

    Usuario updateUsuario(int id, Usuario usuario);

    Usuario updateUsuarioPerfil(int id, Usuario usuario);

    Integer getMonedasById(int id);

    boolean existsByEmail(String email);

}
