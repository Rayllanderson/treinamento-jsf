package com.ray.treinamentojsf;

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
    
    public String save() {
	dao.save(pessoa);
	pessoa = new Pessoa();
	return "";
    }

    public Pessoa getPessoa() {
	return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
	this.pessoa = pessoa;
    }
}
