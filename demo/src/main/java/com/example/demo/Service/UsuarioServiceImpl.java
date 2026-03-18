package com.example.demo.Service;

import com.example.demo.Model.Usuario;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAllConPerfil();
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        if (usuario.getPerfil() != null) {
            usuario.getPerfil().setUsuario(usuario);
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        return usuarioRepository.findById(id).map(existente -> {
            existente.setMombre(usuarioActualizado.getMombre());
            existente.setCorreo(usuarioActualizado.getCorreo());
            return usuarioRepository.save(existente);
        }).orElseThrow(() -> new RuntimeException("Error: Usuario no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("No se puede eliminar. Usuario no encontrado con id: " + id);
        }
    }

    @Override
    public List<Object[]> listarConPerfilNativo() {
        return usuarioRepository.findUsuariosConPerfilNativo();
    }
}