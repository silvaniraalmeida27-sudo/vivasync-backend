package com.vivasync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivasync.dto.LoginRequest;
import com.vivasync.dto.LoginResponse;
import com.vivasync.entity.Usuario;
import com.vivasync.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<Usuario> listarTodos() {
        return usuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return usuarioService.salvar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario usuarioExistente = usuarioService.buscarPorId(id);

        if (usuarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioExistente.setNome(usuario.getNome());
        usuarioExistente.setEmail(usuario.getEmail());
        usuarioExistente.setSenha(usuario.getSenha());

        return ResponseEntity.ok(usuarioService.salvar(usuarioExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Usuario usuario = usuarioService.buscarPorId(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        Usuario usuario = usuarioService.autenticar(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (usuario == null) {
            return ResponseEntity.status(401).build();
        }

        LoginResponse response = new LoginResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());

        return ResponseEntity.ok(response);
    }
}