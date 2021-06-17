package br.com.project.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.project.domain.Cliente;
import br.com.project.dto.ClienteDTO;
import br.com.project.repositories.ClienteRepository;
import br.com.project.resources.exceptions.FieldMessage;

public class AtualizacaoClienteValidator implements ConstraintValidator<AtualizacaoCliente, ClienteDTO> {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(AtualizacaoCliente ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Long uriId =Long.valueOf(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<>();

		Cliente aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriId))
			list.add(new FieldMessage("email", "email já existente"));
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}