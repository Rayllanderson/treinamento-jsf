package com.ray.treinamentojsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ray.dao.GenericDao;
import com.ray.entities.User;
import com.ray.impl.UserDao;
import com.ray.repository.UserRepository;

@ViewScoped
@ManagedBean(name = "UserBean")
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user = new User();
    private GenericDao<User> dao = new GenericDao<>();
    private List<User> users = new ArrayList<>();
    private UserRepository userDao = new UserDao();

    public String login() {
	User u = null;
	u = userDao.login(user.getLogin(), user.getPassword());
	if (u != null) {
	    FacesContext context = FacesContext.getCurrentInstance();
	    ExternalContext externalContext = context.getExternalContext();
	    externalContext.getSessionMap().put("user", u);
	    return "pessoas.xhtml";
	}
	return "login.xhtml";
    }

    public String logout() {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	externalContext.getSessionMap().put("user", null);
	return "login.xhtml";
    }

    public String novo() {
	user = new User();
	return "";
    }

    public String save() {
	user = dao.save(user);
	carregarUsers();
	return "";
    }

    public String remove() {
	dao.remove(user);
	novo();
	carregarUsers();
	return "";
    }

    @PostConstruct // carregar ao iniciar
    public void carregarUsers() {
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
