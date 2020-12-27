package com.ray.impl;

import javax.persistence.EntityManager;

import com.ray.entities.User;
import com.ray.repository.UserRepository;
import com.ray.util.JPAUtil;

public class UserDao implements UserRepository {

    private EntityManager em = JPAUtil.getEntityManager();

    @Override
    public User login(String username, String password) {
	try {
	    return (User) em.createQuery(
		    "select u from User u where u.login = '" + username + "' and u.password = '" + password + "'")
		    .getSingleResult();
	} catch (Exception e) {
	    return null;
	}
    }

}
