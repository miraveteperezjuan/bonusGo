package com.bonusGo.Bonus.Go.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Tipo {
    ROPA(1, "Ropa", "Prendas de vestir y artículos de moda diseñados para el día a día, ocasiones especiales y actividades deportivas."),
    EXPERIENCIA(2, "Experiencia", "Eventos y actividades únicas que incluyen entretenimiento, viajes, gastronomía y aventuras emocionantes."),
    TARJETAS(3, "Tarjetas Regalo", "Tarjetas de regalo utilizables en múltiples comercios, ideales para obsequios y recompensas personalizadas.");

    private int id_Tipo;
    private String nombre, descripcion;

    public void setId_Tipo(int id_Tipo) {
        this.id_Tipo = id_Tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
