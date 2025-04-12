package com.bonusGo.Bonus.Go.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ganancia_monedas")
public class GananciaMonedas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Ganancia")
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "ID_Usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_Objetivo")
    private Objetivo objetivo;

    private boolean canjeado;

    public GananciaMonedas(int idTransaccion, Usuario usuario, Objetivo objetivo, boolean canjeado) {
        this.idTransaccion = idTransaccion;
        this.usuario = usuario;
        this.objetivo = objetivo;
        this.canjeado = canjeado;
    }

    public GananciaMonedas(Usuario usuario, Objetivo objetivo, boolean canjeado) {
        this.usuario = usuario;
        this.objetivo = objetivo;
        this.canjeado = canjeado;
    }

    public GananciaMonedas() {
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isCanjeado() {
        return canjeado;
    }

    public void setCanjeado(boolean canjeado) {
        this.canjeado = canjeado;
    }
}
