package br.com.project.domain.enums;

public enum TipoCliente {
	
	
	PESSOAFISICA(1L, "Pessoa Física"),
	PESSOAJURIDICA(2L, "Pessoa Jurídica");
	
	private Long cod;
	private String descricao;
	
	private TipoCliente(Long cod, String descricao) {
		this.cod = cod;
		this.descricao = this.descricao;
	}

	public Long getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Long cod) {
		
		if (cod == null)
			return null;
		
		for(TipoCliente t: TipoCliente.values()) {			
			if(cod.equals( t.getCod()))
				return t;			
		}		
		
		throw new IllegalArgumentException("Codigo Digitado inválido " + cod);
	}
	
}
