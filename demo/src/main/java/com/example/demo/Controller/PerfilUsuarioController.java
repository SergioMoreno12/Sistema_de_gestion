package com.example.demo.Controller;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Service.PerfilUsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/perfiles")
public class PerfilUsuarioController {

    private final PerfilUsuarioServiceImpl perfilService;

    public PerfilUsuarioController(PerfilUsuarioServiceImpl perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<PerfilUsuario>> listar() {
        return ResponseEntity.ok(perfilService.listar());
    }

    @GetMapping("/buscar/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(perfilService.buscarPorUsuario(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody PerfilUsuario perfil, @RequestParam Long usuarioId) {
        try {
            return ResponseEntity.status(201).body(perfilService.guardar(perfil, usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody PerfilUsuario perfil) {
        try {
            return ResponseEntity.ok(perfilService.actualizar(id, perfil));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            perfilService.eliminar(id);
            return ResponseEntity.ok("Perfil eliminado exitosamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}