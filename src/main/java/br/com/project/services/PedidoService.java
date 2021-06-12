package br.com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.project.domain.Pedido;
import br.com.project.repositories.PedidoRepository;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository PedidoRepo;
	
	public List<Pedido> buscarTodos() {
		return PedidoRepo.findAll();
	}
	
	public Pedido buscarPorId(Long id) {
		Pedido Pedido = PedidoRepo.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Pedido não encontrado,"
						+ " para o id = " + id));
		return Pedido;		
	}

}
