package com.example.demo.Service;

import com.example.demo.Model.Estudiante;
import java.util.List;

public interface EstudianteService {
    List<Estudiante> Listar();
    Estudiante guardar(Estudiante estudiante);
    Estudiante actualizar(Long id, Estudiante estudiante);
    void eliminar(Long id);
    Estudiante inscribir(Long estudianteId, Long cursoId);
    Estudiante desinscribir(Long estudianteId, Long cursoId);
    List<Object[]> listarNativoPorCurso(Long cursoId);
}