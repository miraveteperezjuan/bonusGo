package com.bonusGo.Bonus.Go.model;


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
@Table(name = "objetivos")
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_objetivo;
    @Column
    private String nombre;
    @Column
    private String descripcion;
    @Column
    private int monedas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ganancia_monedas", joinColumns = @JoinColumn(name = "id_objetivo"),
            inverseJoinColumns = @JoinColumn(name = "id_Usuario"))
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
