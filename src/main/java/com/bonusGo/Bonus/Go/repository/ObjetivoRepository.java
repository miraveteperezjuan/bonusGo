package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

    // metodo para realizar busqueda de los objetivos por su categoria
    List<Objetivo> getByCategoria(int idObjetivo);
}
