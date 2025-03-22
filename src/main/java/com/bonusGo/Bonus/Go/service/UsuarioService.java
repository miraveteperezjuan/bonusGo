package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.dto.LoginDTO;
import com.bonusGo.Bonus.Go.dto.UsuarioDTO;
import com.bonusGo.Bonus.Go.payload.LoginMesage;

public interface UsuarioService {

    String add(UsuarioDTO usuarioDTO);

    LoginMesage loginUsuario(LoginDTO loginDTO);



}
