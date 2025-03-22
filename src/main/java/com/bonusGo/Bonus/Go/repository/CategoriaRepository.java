package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    // metodo para realizar consultas de categorias de productos por nombre
    List<Categoria> getByCategoria(String nombreCategoria);
}
