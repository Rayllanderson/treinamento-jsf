package com.ray.beans;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;

/*
 * @RequestScoped; //morre a cada requisição - padrão
 * 
 * @ViewScoped; // segunda os dados, os dados são mantidos em tela. 
 * só morre se for redirecionado / abrir o link novamente. 
 * 
 * @SessionScoped; // mantida a cada sessão. só morre quando a sessão acabar.
 * 
 * @ApplicationScoped - mantida para todos os usuários
 * 
 * 
 * 
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.annotation.PostConstruct;

import com.google.gson.Gson;
import com.ray.dao.GenericDao;
import com.ray.entities.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Pessoa pessoa = new Pessoa();
    private GenericDao<Pessoa> dao = new GenericDao<>();
    private List<Pessoa> pessoas = new ArrayList<>();
    
    public String save() {
	pessoa = dao.save(pessoa);
	carregarPessoas();
	showMessage("Sucesso!");
	return "";
    }
    
    public String novo() {
	pessoa = new Pessoa();
	return "";
    }
    
    public String remove() {
	dao.remove(pessoa);
	novo();
	carregarPessoas();
	showMessage("Removido com sucesso!");
	return "";
    }
    
    @PostConstruct //carregar ao iniciar
    public void carregarPessoas() {
	pessoas = dao.findAll(Pessoa.class);
    }
    
    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
	return pessoas;
    }
    
    private void showMessage(String msg) {
	FacesContext context = FacesContext.getCurrentInstance();
	FacesMessage message = new FacesMessage(msg);
	context.addMessage(null, message);
    }
    
    public void pesquisaCep(AjaxBehaviorEvent e) {
	try {
	    String param = pessoa.getCep();
	    String urlStr = "https://viacep.com.br/ws/" + param +"/json/";
	    URL url = new URL(urlStr);
	    URLConnection connection = url.openConnection();
	    InputStream is = connection.getInputStream();
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    String cep = "";
	    StringBuilder jsonCep = new StringBuilder();
	    while((cep = bufferedReader.readLine()) != null) {
		jsonCep.append(cep);
	    }
	    System.out.println("hey..");
	    //setar atrbibutos de endereco... avenida, complemento, logradouro, bairro, enfim. como? bem... assim:
	    // gsonAux irá setar os atributos de acordo com o json automaticamente, dai só passar pra classe pessoa
	    Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);
	    this.pessoa.setEstado(gsonAux.getEstado());
	    this.pessoa.setCidade(gsonAux.getCidade());
	    
	}catch (Exception ex) {
	    ex.printStackTrace();
	    showMessage("erro ao consultar o cep");
	}
    }
}
