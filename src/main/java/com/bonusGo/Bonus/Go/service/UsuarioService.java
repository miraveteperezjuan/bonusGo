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

    Usuario updateMonedas(int id, int nuevaMoneda);

    boolean existsByEmail(String email);

}
