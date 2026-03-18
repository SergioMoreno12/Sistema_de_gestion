package com.example.demo.Repository;

import com.example.demo.Model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, Long> {

    // Metodo derivado
    Optional<PerfilUsuario> findByDocumento(String documento);
    Optional<PerfilUsuario> findByUsuarioId(Long usuarioId);

    // JPQL – perfil con usuario cargado
    @Query("SELECT p FROM PerfilUsuario p JOIN FETCH p.usuario WHERE p.usuario.id = :uid")
    Optional<PerfilUsuario> findByUsuarioConDetalle(@Param("uid") Long uid);

    // Consulta nativa
    @Query(value = "SELECT * FROM perfiles_usuario WHERE usuario_id = :uid", nativeQuery = true)
    Optional<PerfilUsuario> findByUsuarioNativo(@Param("uid") Long uid);
}