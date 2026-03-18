package com.example.demo.Controller;

import com.example.demo.Model.Libro;
import com.example.demo.Service.LibroServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final LibroServiceImpl libroService;

    public LibroController(LibroServiceImpl libroService) {
        this.libroService = libroService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Libro>> listar() {
        return ResponseEntity.ok(libroService.listar());
    }

    @GetMapping("/listar/categoria/{catId}")
    public ResponseEntity<List<Libro>> listarPorCategoria(@PathVariable Long catId) {
        return ResponseEntity.ok(libroService.listarPorCategoria(catId));
    }

    @GetMapping("/listar/nativo/categoria/{catId}")
    public ResponseEntity<List<Object[]>> listarNativo(@PathVariable Long catId) {
        return ResponseEntity.ok(libroService.listarNativoPorCategoria(catId));
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Libro libro, @RequestParam Long categoriaId) {
        try {
            return ResponseEntity.status(201).body(libroService.guardar(libro, categoriaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Libro libro,
                                        @RequestParam(required = false) Long categoriaId) {
        try {
            return ResponseEntity.ok(libroService.actualizar(id, libro, categoriaId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            libroService.eliminar(id);
            return ResponseEntity.ok("Libro eliminado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}