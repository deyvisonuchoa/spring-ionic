package br.com.project.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.domain.Categoria;
import br.com.project.domain.Cliente;
import br.com.project.dto.CadastroClienteDTO;
import br.com.project.dto.ClienteDTO;
import br.com.project.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource{
	
	@Autowired
	ClienteService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar() {	
		List<Cliente> lista = service.buscarTodos();		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		
		Cliente obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pageable")
	public ResponseEntity<Page<ClienteDTO>> listarPaginas(Pageable page) {	
		Page<ClienteDTO> lista = service.findPage(page);
		return ResponseEntity.ok().body(lista);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CadastroClienteDTO objDto){
		Cliente obj = service.fromDTO(objDto);
		service.insert(obj);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> insert(@PathVariable Long id,@Valid  @RequestBody ClienteDTO cat){
		service.update(id, service.fromDTO(cat));
		return ResponseEntity.noContent().build();		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> insert(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}
	
}
