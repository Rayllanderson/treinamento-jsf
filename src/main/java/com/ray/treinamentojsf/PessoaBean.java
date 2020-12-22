package com.ray.treinamentojsf;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;

/*
 * RequestScoped; //morre a cada requisição	
 * 
 * ViewScoped; // segunda os dados, os dados são mantidos em tela. 
 * só morre se for redirecionado / abrir o link novamente. 
 * 
 * SessionScoped; // mantida a cada sessão. só morre quando a sessão acabar.
 * 
 * 
 * 
 * 
 * 
 */



import javax.faces.bean.ManagedBean;

@ApplicationScoped
@ManagedBean(name = "pessoaBean")
public class PessoaBean {

    private String nome;

    private List<String> nomes = new ArrayList<String>();

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String addNome() {
	nomes.add(nome);
	return "";
    }

    public List<String> getNomes() {
	return nomes;
    }
}
