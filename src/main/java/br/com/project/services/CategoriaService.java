package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.domain.Categoria;
import br.com.project.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	public List<Categoria> BuscarTodos() {
		return repo.findAll();
	}
	
	public Categoria BuscarPorId(Long id) {
		Categoria obj = repo.findById(id).get();
		return obj;
	}
	
}
