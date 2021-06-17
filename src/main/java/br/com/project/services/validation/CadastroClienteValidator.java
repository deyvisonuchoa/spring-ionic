package br.com.project.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.project.domain.enums.TipoCliente;
import br.com.project.dto.CadastroClienteDTO;
import br.com.project.resources.exceptions.FieldMessage;
import br.com.project.services.validation.utils.BR;

public class CadastroClienteValidator implements ConstraintValidator<CadastroCliente, CadastroClienteDTO> {

	@Override
	public void initialize(CadastroCliente ann) {
	}

	@Override
	public boolean isValid(CadastroClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF INVALIDO"));
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ INVALIDO"));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}