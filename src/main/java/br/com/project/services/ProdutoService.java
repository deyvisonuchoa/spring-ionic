package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.domain.Categoria;
import br.com.project.domain.Produto;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.repositories.ProdutoRepository;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public List<Produto> findAll() {
		return produtoRepo.findAll();
	}
	
	public Produto findById(Long id) {
		Produto Produto = produtoRepo.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Produto não encontrado,"
						+ " para o id = " + id));
		return Produto;		
	}
	
	public Page<Produto> search(String nome, List<Long> ids, Pageable pageable){
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageable);
	}

}
