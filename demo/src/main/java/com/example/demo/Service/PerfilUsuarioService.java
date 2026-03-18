package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import java.util.List;

public interface PerfilUsuarioService {
    List<PerfilUsuario> listar();
    PerfilUsuario guardar(PerfilUsuario perfil, Long usuarioId);
    PerfilUsuario actualizar(Long id, PerfilUsuario perfil);
    void eliminar(Long id);
    PerfilUsuario buscarPorUsuario(Long usuarioId);
}