package com.vivasync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivasync.entity.Emocao;
import com.vivasync.service.EmocaoService;

@RestController
@RequestMapping("/api/emocoes")
public class EmocaoController {

    private final EmocaoService emocaoService;

    public EmocaoController(EmocaoService emocaoService) {
        this.emocaoService = emocaoService;
    }

    @GetMapping
    public List<Emocao> listarTodos() {
        return emocaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emocao> buscarPorId(@PathVariable Long id) {
        Emocao emocao = emocaoService.buscarPorId(id);

        if (emocao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(emocao);
    }

    @PostMapping
    public Emocao criar(@RequestBody Emocao emocao) {
        return emocaoService.salvar(emocao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emocao> atualizar(
            @PathVariable Long id,
            @RequestBody Emocao emocao) {

        Emocao emocaoExistente = emocaoService.buscarPorId(id);

        if (emocaoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        emocaoExistente.setNome(emocao.getNome());
        emocaoExistente.setDescricao(emocao.getDescricao());

        return ResponseEntity.ok(emocaoService.salvar(emocaoExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Emocao emocao = emocaoService.buscarPorId(id);

        if (emocao == null) {
            return ResponseEntity.notFound().build();
        }

        emocaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}