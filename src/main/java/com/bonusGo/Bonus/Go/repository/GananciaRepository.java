package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.GananciaMonedas;
import com.bonusGo.Bonus.Go.model.Objetivo;
import com.bonusGo.Bonus.Go.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GananciaRepository extends JpaRepository<GananciaMonedas, Integer> {

    @Query("SELECT t.objetivo FROM GananciaMonedas t WHERE t.usuario.id_Usuario = :userId AND t.canjeado = true")
    List<Objetivo> findObjetivosCanjeadosByUsuarioId(int userId);

}
