package com.example.demo.Controller;

import com.example.demo.Model.Curso;
import com.example.demo.Service.CursoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoServiceImpl cursoService;

    public CursoController(CursoServiceImpl cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Curso>> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/listar/estadisticas")
    public ResponseEntity<List<Object[]>> listarEstadisticas() {
        return ResponseEntity.ok(cursoService.listarEstadisticas());
    }

    @PostMapping("/guardar")
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso) {
        return ResponseEntity.status(201).body(cursoService.guardar(curso));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.actualizar(id, curso));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            cursoService.eliminar(id);
            return ResponseEntity.ok("Curso eliminado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}