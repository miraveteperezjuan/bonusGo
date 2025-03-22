package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolRepository, Integer> {

    // metodo para consultar el rol de los usuarios
    Rol getRol(String nombreRol);
}
