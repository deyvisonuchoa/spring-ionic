package br.com.project.domain.mestra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.project.domain.Endereco;

@Entity
public class OrdemFase implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Long id;
	private Long ordem;
	
	@ManyToOne
    @JoinColumn(name="fase")
	private FaseProjeto fase;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name="metodologia")
	private Metodologia metodologia; 
	
	public OrdemFase() {		
	}	

	public OrdemFase(Long id, Long ordem, FaseProjeto fase, Metodologia metodologia) {
        super();
        this.id = id;
        this.ordem = ordem;
        this.fase = fase;
        this.metodologia = metodologia;
    }

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    public Long getOrdem() {
        return ordem;
    }

    public void setOrdem(Long ordem) {
        this.ordem = ordem;
    }

    public FaseProjeto getFase() {
        return fase;
    }

    public void setFase(FaseProjeto fase) {
        this.fase = fase;
    }

    public Metodologia getMetodologia() {
        return metodologia;
    }	
	
}
