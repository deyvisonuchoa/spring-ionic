package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.domain.Cliente;
import br.com.project.repositories.ClienteRepository;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	public List<Cliente> buscarTodos() {
		return clienteRepo.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Cliente não encontrado,"
						+ " para o id = " + id));
		return cliente;		
	}

}
