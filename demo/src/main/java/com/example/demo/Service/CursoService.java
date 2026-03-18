package com.example.demo.Service;

import com.example.demo.Model.Curso;
import java.util.List;

public interface CursoService {
    List<Curso> listar();
    Curso guardar(Curso curso);
    Curso actualizar(Long id, Curso curso);
    void eliminar(Long id);
    List<Object[]> listarEstadisticas();
}