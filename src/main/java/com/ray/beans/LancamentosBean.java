package com.ray.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.ray.dao.GenericDao;
import com.ray.entities.Lancamento;
import com.ray.entities.User;
import com.ray.impl.LancamentoDao;
import com.ray.repository.LancamentoRepository;

@ViewScoped
@ManagedBean(name = "lancamentosBean")
public class LancamentosBean {

    private Lancamento lancamento = new Lancamento();
    private GenericDao<Lancamento> dao = new GenericDao<>();
    private LancamentoRepository repository = new LancamentoDao();
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
	User user = this.getUser();
	this.lancamento.setUser(user);
	dao.save(lancamento);
	carregarLancamentos();
	showMessage("Sucesso!");
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
	showMessage("Removido com sucesso!");
	return "";
    }
    
    @PostConstruct //carregar ao iniciar
    public void carregarLancamentos() {
	lancamentos = repository.findAll(getUser().getId());
    }
    
    private User getUser() {
	FacesContext context = FacesContext.getCurrentInstance();
	ExternalContext externalContext = context.getExternalContext();
	return (User) externalContext.getSessionMap().get("user");
    }
    
    private void showMessage(String msg) {
	FacesContext context = FacesContext.getCurrentInstance();
	FacesMessage message = new FacesMessage(msg);
	context.addMessage(null, message);
    }
}
