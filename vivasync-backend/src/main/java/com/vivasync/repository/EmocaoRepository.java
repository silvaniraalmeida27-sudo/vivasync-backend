package com.vivasync.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivasync.entity.Emocao;

public interface EmocaoRepository extends JpaRepository<Emocao, Long> {

}