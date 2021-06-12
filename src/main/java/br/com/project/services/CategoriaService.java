package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.domain.Categoria;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repo;
	
	public List<Categoria> find() {
		return repo.findAll();
	}
	
	public Categoria findById(Long id) {
		
		Categoria obj = repo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id 
						+ ", tipo: " + Categoria.class.getName()));
		
		return obj;
	}

	public Categoria insert(Categoria cat) {
		
		cat.setId(null);
		
		return repo.save(cat);
	}
	
	public Categoria update(Long id, Categoria cat) {
		cat.setId(id);
		
		findById(cat.getId());
		
		return repo.save(cat);
	}
	
}
