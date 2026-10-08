package com.vivasync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vivasync.entity.Alimentacao;
import com.vivasync.service.AlimentacaoService;

@RestController
@RequestMapping("/api/alimentacoes")
public class AlimentacaoController {

    private final AlimentacaoService alimentacaoService;

    public AlimentacaoController(AlimentacaoService alimentacaoService) {
        this.alimentacaoService = alimentacaoService;
    }

    @GetMapping
    public List<Alimentacao> listarTodos() {
        return alimentacaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alimentacao> buscarPorId(@PathVariable Long id) {
        Alimentacao alimentacao = alimentacaoService.buscarPorId(id);

        if (alimentacao == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alimentacao);
    }

    @PostMapping
    public Alimentacao criar(@RequestBody Alimentacao alimentacao) {
        return alimentacaoService.salvar(alimentacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alimentacao> atualizar(
            @PathVariable Long id,
            @RequestBody Alimentacao alimentacao) {

        Alimentacao alimentacaoExistente = alimentacaoService.buscarPorId(id);

        if (alimentacaoExistente == null) {
            return ResponseEntity.notFound().build();
        }

        alimentacaoExistente.setNome(alimentacao.getNome());
        alimentacaoExistente.setDescricao(alimentacao.getDescricao());

        return ResponseEntity.ok(alimentacaoService.salvar(alimentacaoExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        Alimentacao alimentacao = alimentacaoService.buscarPorId(id);

        if (alimentacao == null) {
            return ResponseEntity.notFound().build();
        }

        alimentacaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}