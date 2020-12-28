package com.ray.impl;

import java.util.List;

import javax.persistence.EntityManager;

import com.ray.entities.Lancamento;
import com.ray.repository.LancamentoRepository;
import com.ray.util.JPAUtil;

public class LancamentoDao implements LancamentoRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    @SuppressWarnings("unchecked")
    @Override
    public List<Lancamento> findAll(Long userId) {
	try {
	    return  em.createQuery(
		    "select l from Lancamento l where l.user.id = " +  userId).getResultList();
	} catch (Exception e) {
	    return null;
	}
    }

}
