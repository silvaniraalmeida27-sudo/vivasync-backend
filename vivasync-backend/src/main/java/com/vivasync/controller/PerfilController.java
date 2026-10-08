package com.vivasync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivasync.entity.Perfil;
import com.vivasync.service.PerfilService;

@RestController
@RequestMapping("/api/perfis")
public class PerfilController {

    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping
    public List<Perfil> listarTodos() {
        return perfilService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        Perfil perfil = perfilService.buscarPorId(id);

        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(perfil);
    }

    @PostMapping
    public Perfil criar(@RequestBody Perfil perfil) {
        return perfilService.salvar(perfil);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar(
            @PathVariable Long id,
            @RequestBody Perfil perfil) {

        Perfil perfilExistente = perfilService.buscarPorId(id);

        if (perfilExistente == null) {
            return ResponseEntity.notFound().build();
        }

        perfilExistente.setTelefone(perfil.getTelefone());
        perfilExistente.setDataNascimento(perfil.getDataNascimento());
        perfilExistente.setObjetivo(perfil.getObjetivo());

        return ResponseEntity.ok(perfilService.salvar(perfilExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Perfil perfil = perfilService.buscarPorId(id);

        if (perfil == null) {
            return ResponseEntity.notFound().build();
        }

        perfilService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}