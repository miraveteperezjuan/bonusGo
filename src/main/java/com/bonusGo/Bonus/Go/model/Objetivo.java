package com.bonusGo.Bonus.Go.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "objetivos")
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_objetivo;
    @Column(length = 30)
    private String nombre;
    @Column
    private String descripcion;
    @Column(length = 4)
    private Integer monedas;
    @Column(nullable = false)
    private boolean isEnabled  = true;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "ganancia_monedas", joinColumns = @JoinColumn(name = "id_objetivo"),
            inverseJoinColumns = @JoinColumn(name = "id_Usuario"))
    @JsonIgnore
    private List<Usuario> usuarios;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    public int getId_objetivo() {
        return id_objetivo;
    }

    public void setId_objetivo(int id_objetivo) {
        this.id_objetivo = id_objetivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getMonedas() {
        return monedas;
    }

    public void setMonedas(Integer monedas) {
        this.monedas = monedas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
