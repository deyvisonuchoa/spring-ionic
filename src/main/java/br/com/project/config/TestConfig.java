package br.com.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.project.domain.Categoria;
import br.com.project.repositories.CategoriaRepository;

@Configuration
public class TestConfig implements CommandLineRunner{
	
	@Autowired
	CategoriaRepository categoriaRepo;

	@Override
	public void run(String... args) throws Exception {		
		
		Categoria c1 = new Categoria(1L, "informatica");
		Categoria c2 = new Categoria(2L, "escritorio");
		
		categoriaRepo.saveAll(Arrays.asList(c1,c2));
		
	}

}
