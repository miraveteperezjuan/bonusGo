package com.bonusGo.Bonus.Go.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Usuario;
    @Column(length = 30)
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String correo;
    @Column(length = 15)
    private String telefono;
    @Column
    private int moneda;
    @Column
    private String password;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_Rol")
    private Rol rol;

public Usuario(int id_Usuario, String nombre, String apellido, String correo, String telefono, String password) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
    }


    public Usuario(int id_Usuario, String nombre, String apellido, String correo, String telefono, String password, Rol rol) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
    }
}
