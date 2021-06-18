package br.com.project.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.domain.Produto;
import br.com.project.dto.ProdutoDTO;
import br.com.project.resources.utils.URL;
import br.com.project.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource{
	
	@Autowired
	ProdutoService service;

	@GetMapping
	public ResponseEntity<List<Produto>> listar() {	
		List<Produto> lista = service.findAll();		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
		
		Produto obj = service.findById(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping( value = "/pageable")
	public ResponseEntity<Page<ProdutoDTO>> listarPaginas(
			Pageable page, @RequestParam(value = "nome") String nome,
			@RequestParam(value = "categorias") String categorias) {	
		
		Page<Produto> lista = service.search( URL.decodeParam(nome),  URL.decodeLongList(categorias),  page);
		
		Page<ProdutoDTO> listDto = lista.map( i -> new ProdutoDTO(i));
		
		return ResponseEntity.ok().body(listDto);
	}
}
