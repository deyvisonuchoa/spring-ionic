package br.com.project.resources;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.project.domain.Categoria;
import br.com.project.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource{
	
	@Autowired
	CategoriaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {	
		List<Categoria> lista = service.find();		
		return ResponseEntity.ok().body(lista);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarPorId(@PathVariable Long id) {
		
		Categoria obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria cat){
		cat = service.insert(cat);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(cat.getId()).toUri();
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> insert(@PathVariable Long id, @RequestBody Categoria cat){
		cat = service.update(id, cat);
		return ResponseEntity.noContent().build();		
	}
}
