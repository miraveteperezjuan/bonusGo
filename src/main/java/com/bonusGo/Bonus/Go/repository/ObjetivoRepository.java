package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Categoria;
import com.bonusGo.Bonus.Go.model.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

}
