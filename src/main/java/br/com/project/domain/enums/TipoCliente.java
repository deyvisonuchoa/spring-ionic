package br.com.project.domain.enums;

public enum TipoCliente {
	
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = this.descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod == null)
			return null;
		
		for(TipoCliente t: TipoCliente.values()) {			
			if(cod.equals( t.getCod()))
				return t;			
		}		
		
		throw new IllegalArgumentException("Codigo Digitado inválido " + cod);
	}
	
}
