package br.com.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.project.domain.Categoria;
import br.com.project.domain.Produto;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.repositories.ProdutoRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepo;
	
	@Autowired
	ProdutoRepository produtoRepo;

	@Override
	public void run(String... args) throws Exception {		
		
		Categoria cat1 = new Categoria(1L, "informatica");
		Categoria cat2 = new Categoria(2L, "escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		produtoRepo.saveAll(Arrays.asList(p1,p2,p3));
		
	}

}
