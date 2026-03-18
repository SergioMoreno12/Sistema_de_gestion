package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    List<Categoria> listarConLibros();
    Categoria guardar(Categoria categoria);
    Categoria actualizar(Long id, Categoria categoria);
    void eliminar(Long id);
    List<Categoria> buscarNativo(String nombre);
}