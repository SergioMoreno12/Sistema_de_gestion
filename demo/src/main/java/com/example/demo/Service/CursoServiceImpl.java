package com.example.demo.Service;

import com.example.demo.Model.Curso;
import com.example.demo.Repository.CursoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAllConEstudiantes();
    }

    @Override
    public Curso guardar(Curso curso) {
        return cursoRepository.save(curso);
    }

    @Override
    public Curso actualizar(Long id, Curso cursoActualizado) {
        return cursoRepository.findById(id).map(existente -> {
            existente.setNombres(cursoActualizado.getNombres());
            existente.setCreditos(cursoActualizado.getCreditos());
            return cursoRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Error: Curso no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Curso no encontrado con id: " + id);
        }
    }

    @Override
    public List<Object[]> listarEstadisticas() {
        return cursoRepository.findCursosConTotalEstudiantes();
    }
}