package br.com.project.resources;

import java.io.Serializable;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource implements Serializable{
	private static final long serialVersionUID = 1L;

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "Rest funcionando";
	}
}
