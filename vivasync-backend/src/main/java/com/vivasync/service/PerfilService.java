package com.vivasync.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vivasync.entity.Perfil;
import com.vivasync.repository.PerfilRepository;

@Service
public class PerfilService {

    private final PerfilRepository perfilRepository;

    public PerfilService(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Perfil buscarPorId(Long id) {
        return perfilRepository.findById(id).orElse(null);
    }

    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public void excluir(Long id) {
        perfilRepository.deleteById(id);
    }
}