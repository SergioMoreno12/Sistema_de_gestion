package com.example.demo.Repository;

import com.example.demo.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    // Metodo derivado
    Optional<Categoria> findByNombre(String nombre);

    // JPQL – carga libros en la misma consulta (evita N+1)
    @Query("SELECT DISTINCT c FROM Categoria c LEFT JOIN FETCH c.libros")
    List<Categoria> findAllConLibros();

    // JPQL – una categoría con sus libros
    @Query("SELECT c FROM Categoria c LEFT JOIN FETCH c.libros WHERE c.id = :id")
    Optional<Categoria> findByIdConLibros(@Param("id") Long id);

    // Consulta nativa
    @Query(value = "SELECT * FROM categorias WHERE nombre ILIKE %:nombre%", nativeQuery = true)
    List<Categoria> buscarNativo(@Param("nombre") String nombre);
}