package com.example.demo.Repository;

import com.example.demo.Model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Metodo derivado
    Optional<Curso> findByNombres(String nombres);
    List<Curso> findByCreditosGreaterThanEqual(int creditos);

    // JPQL – cursos con estudiantes (ManyToMany)
    @Query("SELECT DISTINCT c FROM Curso c LEFT JOIN FETCH c.estudiantes")
    List<Curso> findAllConEstudiantes();

    // Consulta nativa con COUNT y GROUP BY
    @Query(value = "SELECT c.id, c.nombres, c.creditos, COUNT(ec.estudiante_id) AS total " +
            "FROM cursos c LEFT JOIN estudiante_curso ec ON ec.curso_id = c.id " +
            "GROUP BY c.id, c.nombres, c.creditos", nativeQuery = true)
    List<Object[]> findCursosConTotalEstudiantes();
}