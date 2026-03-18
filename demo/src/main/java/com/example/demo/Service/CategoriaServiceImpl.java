package com.example.demo.Service;

import com.example.demo.Model.Categoria;
import com.example.demo.Repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public List<Categoria> listarConLibros() {
        return categoriaRepository.findAllConLibros();
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Long id, Categoria categoriaActualizada) {
        return categoriaRepository.findById(id).map(existente -> {
            existente.setNombre(categoriaActualizada.getNombre());
            existente.setDescripcion(categoriaActualizada.getDescripcion());
            return categoriaRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Error: Categoria no encontrada con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Categoria no encontrada con id: " + id);
        }
    }

    @Override
    public List<Categoria> buscarNativo(String nombre) {
        return categoriaRepository.buscarNativo(nombre);
    }
}