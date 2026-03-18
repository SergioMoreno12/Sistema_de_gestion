package com.example.demo.Service;

import com.example.demo.Model.PerfilUsuario;
import com.example.demo.Model.Usuario;
import com.example.demo.Repository.PerfilUsuarioRepository;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PerfilUsuarioServiceImpl implements PerfilUsuarioService {

    private final PerfilUsuarioRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    public PerfilUsuarioServiceImpl(PerfilUsuarioRepository perfilRepository, UsuarioRepository usuarioRepository) {
        this.perfilRepository = perfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<PerfilUsuario> listar() {
        return perfilRepository.findAll();
    }

    @Override
    public PerfilUsuario guardar(PerfilUsuario perfil, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + usuarioId));
        perfil.setUsuario(usuario);
        return perfilRepository.save(perfil);
    }

    @Override
    public PerfilUsuario actualizar(Long id, PerfilUsuario perfilActualizado) {
        return perfilRepository.findById(id).map(existente -> {
            existente.setDocumento(perfilActualizado.getDocumento());
            existente.setTelefono(perfilActualizado.getTelefono());
            return perfilRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Error: Perfil no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Perfil no encontrado con id: " + id);
        }
    }

    @Override
    public PerfilUsuario buscarPorUsuario(Long usuarioId) {
        return perfilRepository.findByUsuarioConDetalle(usuarioId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario con id: " + usuarioId));
    }
}