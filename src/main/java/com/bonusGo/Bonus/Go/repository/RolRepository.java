package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
