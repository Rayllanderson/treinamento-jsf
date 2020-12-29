package com.ray.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import com.ray.entities.Estado;
import com.ray.repository.EstadoRepository;
import com.ray.util.JPAUtil;

public class EstadoDao implements EstadoRepository{

    private EntityManager em = JPAUtil.getEntityManager();
    
    @Override
    public List<SelectItem> estados() {
	@SuppressWarnings("unchecked")
	List<Estado> estados = em.createQuery("from Estado").getResultList();
	List<SelectItem> selectItems = new ArrayList<>();
	//passando o objeto inteiro, e o que mostrará pro user (select item) é apenas o nome
	estados.forEach(x -> selectItems.add(new SelectItem(x, x.getNome()))); 
	return selectItems;
    }

}
