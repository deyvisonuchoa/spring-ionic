package br.com.project.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.domain.Categoria;
import br.com.project.domain.Pedido;
import br.com.project.dto.CategoriaDTO;
import br.com.project.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource{
	
	@Autowired
	PedidoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> listar() {	
		List<Pedido> lista = service.buscarTodos();		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		
		Pedido obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping(consumes = "application/json")
	public ResponseEntity<Void> insert(@RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
}
