package com.vivasync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivasync.entity.Emocao;
import com.vivasync.repository.EmocaoRepository;

@Service
public class EmocaoService {

    private final EmocaoRepository emocaoRepository;

    public EmocaoService(EmocaoRepository emocaoRepository) {
        this.emocaoRepository = emocaoRepository;
    }

    public List<Emocao> listarTodos() {
        return emocaoRepository.findAll();
    }

    public Emocao buscarPorId(Long id) {
        return emocaoRepository.findById(id).orElse(null);
    }

    public Emocao salvar(Emocao emocao) {
        return emocaoRepository.save(emocao);
    }

    public void excluir(Long id) {
        emocaoRepository.deleteById(id);
    }
}