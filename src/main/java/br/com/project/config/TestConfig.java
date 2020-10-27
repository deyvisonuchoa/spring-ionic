package br.com.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.project.domain.Categoria;
import br.com.project.domain.Cidade;
import br.com.project.domain.Estado;
import br.com.project.domain.Produto;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.repositories.CidadeRepository;
import br.com.project.repositories.EstadoRepository;
import br.com.project.repositories.ProdutoRepository;

@Configuration
public class TestConfig implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepo;
	
	@Autowired
	ProdutoRepository produtoRepo;
	
	@Autowired
	EstadoRepository estadoRepo;
	
	@Autowired
	CidadeRepository cidadeRepo;

	@Override
	public void run(String... args) throws Exception {		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
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
		
		Estado est1 = new Estado(null, "Rio de Janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Rio de janeiro", est1);
		Cidade c2 = new Cidade(null, "Niterói", est1);
		Cidade c3 = new Cidade(null, "Macaé", est1);
		Cidade c4 = new Cidade(null, "São Paulo", est2);
		Cidade c5 = new Cidade(null, "Campinas", est2);
		
		estadoRepo.saveAll(Arrays.asList(est1,est2));
		cidadeRepo.saveAll(Arrays.asList(c1,c2,c3,c4,c5));
	}

}
