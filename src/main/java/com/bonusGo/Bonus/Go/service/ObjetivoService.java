package com.bonusGo.Bonus.Go.service;

import com.bonusGo.Bonus.Go.model.Objetivo;

import java.util.List;

public interface ObjetivoService {

    Objetivo registObjetivo(Objetivo objetivo);

    Objetivo actualizar(int id, Objetivo nuevoObjetivo);

    void eliminarObjetivo(int id);

    List<Objetivo> listarObjetivos();

    Objetivo buscarObjetivo(int id);

    List<Objetivo> listarHabilitados();

    List<Objetivo> listarDeshabilitados();

    void setEstado(int id, boolean habilitado);

    void canjearObjetivo(int idObjetivo, int idUsuario);


}
