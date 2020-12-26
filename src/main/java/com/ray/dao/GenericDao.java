package com.ray.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ray.util.JPAUtil;

public class GenericDao<T> {

    private EntityManager em = JPAUtil.getEntityManager();

    /**
     * Save or update
     * @param entity
     * @return
     */
    public T save(T entity) {
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	entity = em.merge(entity);
	transaction.commit();
	return entity;
    }
    
    public void remove(T entity) {
	EntityTransaction transaction = em.getTransaction();
	transaction.begin();
	em.remove(entity);
	transaction.commit();
    }

}
