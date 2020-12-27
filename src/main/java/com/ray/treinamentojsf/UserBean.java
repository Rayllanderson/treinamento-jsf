package com.ray.treinamentojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ray.dao.GenericDao;
import com.ray.entities.User;

@ViewScoped
@ManagedBean(name = "UserBean")
public class UserBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private User user = new User();
    private GenericDao<User> dao = new GenericDao<>();
    private List<User> users = new ArrayList<>();
    
    public String save() {
	user = dao.save(user);
	carregarPessoas();
	return "";
    }
    
    public String novo() {
	user = new User();
	return "";
    }
    
    public String remove() {
	dao.remove(user);
	novo();
	carregarPessoas();
	return "";
    }
    
    @PostConstruct //carregar ao iniciar
    public void carregarPessoas() {
	users = dao.findAll(User.class);
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
	return users;
    }

    public void setUsers(List<User> users) {
	this.users = users;
    }
    
    
}
