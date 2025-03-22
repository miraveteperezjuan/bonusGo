package com.bonusGo.Bonus.Go.dto;

import com.bonusGo.Bonus.Go.model.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
                      String password) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;

    }

    public UsuarioDTO() {
    }
}
