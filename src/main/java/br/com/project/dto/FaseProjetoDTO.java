package br.com.project.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.project.domain.Categoria;

public class FaseProjetoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	
	private String descricao;
	
	public FaseProjetoDTO() {
		
	}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

	
	
	
	
}
