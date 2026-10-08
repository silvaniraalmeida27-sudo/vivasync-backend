package com.vivasync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivasync.entity.Usuario;
import com.vivasync.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);

        if (usuario == null) {
            return null;
        }

        if (!usuario.getSenha().equals(senha)) {
            return null;
        }

        return usuario;
    }
}