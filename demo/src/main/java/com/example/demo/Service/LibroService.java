package com.example.demo.Service;

import com.example.demo.Model.Libro;
import java.util.List;

public interface LibroService {
    List<Libro> listar();
    Libro guardar(Libro libro, Long categoriaId);
    Libro actualizar(Long id, Libro libro, Long categoriaId);
    void eliminar(Long id);
    List<Libro> listarPorCategoria(Long catId);
    List<Object[]> listarNativoPorCategoria(Long catId);
}