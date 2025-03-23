package com.bonusGo.Bonus.Go.dto;

import com.bonusGo.Bonus.Go.model.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private int id_Usuario;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    //private int moneda;
    private String password;
    private Rol rol;


    public UsuarioDTO(int id_Usuario, String nombre, String apellido, String correo, String telefono,
                      String password, Rol rol) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }


    public UsuarioDTO() {
    }
}
