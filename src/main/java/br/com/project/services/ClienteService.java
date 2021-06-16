package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.domain.Cliente;
import br.com.project.dto.ClienteDTO;
import br.com.project.repositories.ClienteRepository;
import br.com.project.services.exceptions.DataIntegrityException;
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
	
	public Cliente insert(Cliente cat) {
	
		cat.setId(null);
		
		return clienteRepo.save(cat);
	}
	
	public Cliente update(Long id, Cliente obj) {
		Cliente newObj = buscarPorId(id);
		updateData(newObj, obj);
		return clienteRepo.save(newObj);
	}

	public void delete(Long id) {
		buscarPorId(id);
		try {
			clienteRepo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel remover pois existem entidades relacionadas");
		}
	}
	
	
	public Page<ClienteDTO> findPage(Pageable pageable){
		return clienteRepo.findAll(pageable)
				.map( obj -> new ClienteDTO(obj));
	}
	
	public Cliente fromDTO( ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(),
						   objDTO.getEmail(), null, null);
//		throw new UnsupportedOperationException();
	}
	


	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

}
