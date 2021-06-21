package br.com.project.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.domain.Pedido;
import br.com.project.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource{
	
	@Autowired
	PedidoService service;

	@GetMapping
	public ResponseEntity<Page<Pedido>> listar(@PageableDefault(sort = "data", direction = Sort.Direction.DESC) Pageable pageable) {	
	    Page<Pedido> pedidos = service.buscarTodosPaginados(pageable);		
		return ResponseEntity.ok().body(pedidos);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pedido> buscarPorId(@PathVariable Long id) {
		
		Pedido obj = service.buscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Pedido obj){
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
}
