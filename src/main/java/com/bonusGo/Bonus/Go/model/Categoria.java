package com.bonusGo.Bonus.Go.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Categoria {

    BRONCE(1, "Bronce"),
    PLATA(2, "Plata"),
    ORO(3, "Oro");

    private int id_Categoria;
    private String nombre;

    public void setId_Categoria(int id_Categoria) {
        this.id_Categoria = id_Categoria;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
