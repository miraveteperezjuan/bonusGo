package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
