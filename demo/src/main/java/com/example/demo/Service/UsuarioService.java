package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario guardar(Usuario usuario);
    Usuario actualizar(Long id, Usuario usuario);
    void eliminar(Long id);
    List<Object[]> listarConPerfilNativo();
}