package br.com.project.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.project.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource{

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1L, "informatica");
		Categoria c2 = new Categoria(2L, "escritorio");
		List<Categoria> listaCategorias = Arrays.asList(c1,c2);
		
		return listaCategorias;
	}
}
