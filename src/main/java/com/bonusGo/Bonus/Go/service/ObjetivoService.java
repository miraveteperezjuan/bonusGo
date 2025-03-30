package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Objetivo;

import java.util.List;

public interface ObjetivoService {

    Objetivo registObjetivo(Objetivo objetivo, String categoria);

    Objetivo actualizarMonedas(int id, int coste);

    void eliminarObjetivo(int id);

    List<Objetivo> listarObjetivos();

    Objetivo buscarObjetivo(int id);

}
