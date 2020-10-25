package br.com.project.domain;

import java.io.Serializable;

//@Entity
public class Categoria implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//@Id
	private Long id;
	private String nome;
	
	public Categoria() {
		
	}
	
	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

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
	
}
