package com.vivasync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivasync.entity.Treino;
import com.vivasync.service.TreinoService;

@RestController
@RequestMapping("/api/treinos")
public class TreinoController {

    private final TreinoService treinoService;

    public TreinoController(TreinoService treinoService) {
        this.treinoService = treinoService;
    }

    @GetMapping
    public List<Treino> listarTodos() {
        return treinoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treino> buscarPorId(@PathVariable Long id) {
        Treino treino = treinoService.buscarPorId(id);

        if (treino == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(treino);
    }

    @PostMapping
    public Treino criar(@RequestBody Treino treino) {
        return treinoService.salvar(treino);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Treino> atualizar(
            @PathVariable Long id,
            @RequestBody Treino treino) {

        Treino treinoExistente = treinoService.buscarPorId(id);

        if (treinoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        treinoExistente.setNome(treino.getNome());
        treinoExistente.setDescricao(treino.getDescricao());

        return ResponseEntity.ok(treinoService.salvar(treinoExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Treino treino = treinoService.buscarPorId(id);

        if (treino == null) {
            return ResponseEntity.notFound().build();
        }

        treinoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}