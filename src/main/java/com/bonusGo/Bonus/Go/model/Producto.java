package com.bonusGo.Bonus.Go.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Producto;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int coste;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "transacciones", joinColumns = @JoinColumn(name = "id_Producto"),
            inverseJoinColumns = @JoinColumn(name = "id_Usuario"))
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Tipo tipo;

}
