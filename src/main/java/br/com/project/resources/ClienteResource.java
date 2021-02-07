package br.com.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.domain.Categoria;
import br.com.project.domain.Cliente;
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
}
