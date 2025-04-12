package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Objetivo;

import java.util.List;

public interface ObjetivoService {

    Objetivo registObjetivo(Objetivo objetivo);

    Objetivo actualizarMonedas(int id, int monedas);

    void eliminarObjetivo(int id);

    List<Objetivo> listarObjetivos();

    Objetivo buscarObjetivo(int id);


}
