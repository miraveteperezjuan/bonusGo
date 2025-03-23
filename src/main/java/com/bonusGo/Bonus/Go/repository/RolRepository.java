package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("FROM Rol r where r.id_Rol=:id")
    Rol getRol(int id);
}
