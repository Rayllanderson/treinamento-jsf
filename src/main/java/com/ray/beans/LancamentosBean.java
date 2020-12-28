package com.ray.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ray.dao.GenericDao;
import com.ray.entities.Lancamento;
import com.ray.entities.User;

@ViewScoped
@ManagedBean(name = "lancamentosBean")
public class LancamentosBean {

    private Lancamento lancamento = new Lancamento();
    private GenericDao<Lancamento> dao = new GenericDao<>();
    private List<Lancamento> lancamentos = new ArrayList<>();

    public Lancamento getLancamento() {
	return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
	this.lancamento = lancamento;
    }

    public List<Lancamento> getLancamentos() {
	return lancamentos;
    }

    public void setLancamentos(List<Lancamento> lancamentos) {
	this.lancamentos = lancamentos;
    }

    public String save () {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	User user = (User) externalContext.getSessionMap().get("user");
	this.lancamento.setUser(user);
	dao.save(lancamento);
	carregarLancamentos();
	return "";
    }
    
    public String novo() {
	lancamento = new Lancamento();
	return "";
    }
    
    public String remove() {
	dao.remove(lancamento);
	novo();
	carregarLancamentos();
	return "";
    }
    
    @PostConstruct //carregar ao iniciar
    public void carregarLancamentos() {
	lancamentos = dao.findAll(Lancamento.class);
    }
}
