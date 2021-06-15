package br.com.project.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.project.dto.CategoriaDTO;
import br.com.project.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource{
	
	@Autowired
	CategoriaService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> listar() {	
		List<CategoriaDTO> lista = service.find()
				.stream().map( obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());		
		return ResponseEntity.ok().body(lista);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/pageable")
	public ResponseEntity<Page<CategoriaDTO>> listarPaginas(Pageable page) {	
		Page<CategoriaDTO> lista = service.findPage(page);
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
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> insert(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();		
	}
}
