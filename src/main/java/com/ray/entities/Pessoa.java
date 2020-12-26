package com.ray.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pessoa implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String email;
    
    private Sexo sexo;

    @Temporal(TemporalType.DATE) //quero apenas a data
    private Date dataNascimento;

    private String [] frameworks;
    
    public Pessoa() {
    };

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Pessoa other = (Pessoa) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento + "]";
    }

    public String getSexo() {
	return sexo == null ? "" : Sexo.valueOf(this.sexo.getCode()).toString();
    }

    public void setSexo(String sexo) {
	this.sexo = Sexo.valueOf(sexo.toUpperCase());
    }

    public String [] getFrameworks() {
	return frameworks;
    }

    public void setFrameworks(String [] frameworks) {
	this.frameworks = frameworks;
    }

}
