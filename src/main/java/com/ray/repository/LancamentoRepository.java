package com.ray.repository;

import java.util.List;

import com.ray.entities.Lancamento;

public interface LancamentoRepository {
    
    List <Lancamento> findAll(Long userId);
}
