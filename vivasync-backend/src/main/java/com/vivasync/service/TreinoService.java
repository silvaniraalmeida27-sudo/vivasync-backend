package com.vivasync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivasync.entity.Treino;
import com.vivasync.repository.TreinoRepository;

@Service
public class TreinoService {

    private final TreinoRepository treinoRepository;

    public TreinoService(TreinoRepository treinoRepository) {
        this.treinoRepository = treinoRepository;
    }

    public List<Treino> listarTodos() {
        return treinoRepository.findAll();
    }

    public Treino buscarPorId(Long id) {
        return treinoRepository.findById(id).orElse(null);
    }

    public Treino salvar(Treino treino) {
        return treinoRepository.save(treino);
    }

    public void excluir(Long id) {
        treinoRepository.deleteById(id);
    }
}