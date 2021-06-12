package br.com.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.domain.Categoria;
import br.com.project.domain.Pedido;
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
}
