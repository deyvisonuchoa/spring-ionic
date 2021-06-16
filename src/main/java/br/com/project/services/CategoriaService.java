package br.com.project.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.domain.Categoria;
import br.com.project.dto.CategoriaDTO;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.services.exceptions.DataIntegrityException;
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

	public void delete(Long id) {
		findById(id);
		try {
			repo.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel remover uma categoria que possui produtos");
		}
	}
	
	
	public Page<CategoriaDTO> findPage(Pageable pageable){
		return repo.findAll(pageable)
				.map( obj -> new CategoriaDTO(obj));
	}
	
	public Categoria fromDTO( CategoriaDTO obj) {
		return new Categoria(obj.getId(), obj.getNome());
	}
}
