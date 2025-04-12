package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Producto;
import com.bonusGo.Bonus.Go.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransaccionRepository extends JpaRepository<Transaccion, Integer> {

    @Query("SELECT t.producto FROM Transaccion t WHERE t.usuario.id_Usuario = :userId AND t.canjeado = true")
    List<Producto> findProductosCanjeadosByUsuarioId(int userId);

    //boolean existsByUsuario_IdUsuarioAndProducto_IdProducto(int idU, int idP);



}
