package com.example.demo.Repository;

import com.example.demo.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Método derivado
    Optional<Estudiante> findByCorreo(String correo);

    // JPQL – carga los cursos junto con el estudiante (ManyToMany)
    @Query("SELECT DISTINCT e FROM Estudiante e LEFT JOIN FETCH e.cursos")
    List<Estudiante> findAllConCursos();

    // JPQL – estudiantes inscritos en un curso
    @Query("SELECT e FROM Estudiante e JOIN e.cursos c WHERE c.id = :cursoId")
    List<Estudiante> findByCursoId(@Param("cursoId") Long cursoId);

    // Consulta nativa con tabla pivote
    @Query(value = "SELECT e.id, e.nombre, e.correo " +
            "FROM estudiantes e " +
            "INNER JOIN estudiante_curso ec ON ec.estudiante_id = e.id " +
            "WHERE ec.curso_id = :cursoId", nativeQuery = true)
    List<Object[]> findEstudiantesPorCursoNativo(@Param("cursoId") Long cursoId);
}