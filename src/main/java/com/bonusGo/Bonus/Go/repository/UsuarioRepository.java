package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
