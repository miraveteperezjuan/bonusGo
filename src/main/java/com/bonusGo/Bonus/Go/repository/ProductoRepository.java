package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByIsEnabledTrue();
    List<Producto> findByIsEnabledFalse();

}
