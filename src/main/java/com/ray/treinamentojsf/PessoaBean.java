package com.ray.treinamentojsf;

import java.util.ArrayList;
import java.util.List;

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

import com.ray.dao.GenericDao;
import com.ray.entities.Pessoa;

@ViewScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

    private Pessoa pessoa = new Pessoa();
    private GenericDao<Pessoa> dao = new GenericDao<>();
    private List<Pessoa> pessoas = new ArrayList<>();
    
    public String save() {
	pessoa = dao.save(pessoa);
	carregarPessoas();
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
	return "";
    }
    
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
    
    
}
