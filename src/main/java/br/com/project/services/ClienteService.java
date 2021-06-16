package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.domain.Cidade;
import br.com.project.domain.Cliente;
import br.com.project.domain.Endereco;
import br.com.project.domain.enums.TipoCliente;
import br.com.project.dto.CadastroClienteDTO;
import br.com.project.dto.ClienteDTO;
import br.com.project.repositories.ClienteRepository;
import br.com.project.repositories.EnderecoRepository;
import br.com.project.services.exceptions.DataIntegrityException;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public List<Cliente> buscarTodos() {
		return clienteRepo.findAll();
	}
	
	public Cliente buscarPorId(Long id) {
		Cliente cliente = clienteRepo.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Cliente não encontrado,"
						+ " para o id = " + id));
		return cliente;		
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {	
		obj.setId(null);		
		enderecoRepo.saveAll(obj.getEnderecos());
		return clienteRepo.save(obj);
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
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
//		throw new UnsupportedOperationException();
	}
	
	public Cliente fromDTO( CadastroClienteDTO objDTO) {
		
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null)
			cli.getTelefones().add(objDTO.getTelefone2());
		
		if(objDTO.getTelefone3() != null)
			cli.getTelefones().add(objDTO.getTelefone3());
		
		return cli;
	}
	


	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());		
	}

}
