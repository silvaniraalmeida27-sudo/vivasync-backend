package com.vivasync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivasync.entity.Alimentacao;
import com.vivasync.repository.AlimentacaoRepository;

@Service
public class AlimentacaoService {

    private final AlimentacaoRepository alimentacaoRepository;

    public AlimentacaoService(AlimentacaoRepository alimentacaoRepository) {
        this.alimentacaoRepository = alimentacaoRepository;
    }

    public List<Alimentacao> listarTodos() {
        return alimentacaoRepository.findAll();
    }

    public Alimentacao buscarPorId(Long id) {
        return alimentacaoRepository.findById(id).orElse(null);
    }

    public Alimentacao salvar(Alimentacao alimentacao) {
        return alimentacaoRepository.save(alimentacao);
    }

    public void excluir(Long id) {
        alimentacaoRepository.deleteById(id);
    }
}