package com.ray.treinamentojsf;

import javax.faces.bean.ManagedBean;

@ManagedBean(name="pessoaBean")
public class PessoaBean {

    private String nome;
    private String sobrenome;
    private String nomeCompleto;

    public String getSobrenome() {
	return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
	this.sobrenome = sobrenome;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getNomeCompleto() {
	return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
	this.nomeCompleto = nomeCompleto;
    }
    
    public String mostrarNome() {
	nomeCompleto = nome + " " + sobrenome;
	return "";
    }

}
