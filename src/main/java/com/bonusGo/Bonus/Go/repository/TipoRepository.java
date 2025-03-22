package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoRepository extends JpaRepository<Tipo, Integer> {

    // para realizar consultas por nombre del tipo de producto
    List<Tipo> getByTipo(String nombreTipo);
}
