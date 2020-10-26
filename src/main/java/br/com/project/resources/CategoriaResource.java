package br.com.project.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.domain.Categoria;
import br.com.project.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource{
	
	@Autowired
	CategoriaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {		
		Categoria c1 = new Categoria(1L, "informatica");
		Categoria c2 = new Categoria(2L, "escritorio");
		List<Categoria> lista = service.BuscarTodos();		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
		
		Categoria obj = service.BuscarPorId(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
