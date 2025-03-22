package com.bonusGo.Bonus.Go.repository;

import com.bonusGo.Bonus.Go.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // metodo para buscar el rol que tien un usuario en concreto
    Usuario getRol(int idRol);

    // metodo login usuario
    @Query("FROM Usuario u WHERE u.correo = :correo AND u.password = :password")
    Usuario login(@Param("correo") String correo, @Param("pass") String password);

    // metodo consultar saldo del usuario
    Usuario getMonedas(int monedas);

    Optional<Usuario> findOneByEmailAndPassword(String email, String password);

    Usuario findByEmail(String email);


}
