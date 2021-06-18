package br.com.project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.domain.ItemPedido;
import br.com.project.domain.PagamentoComBoleto;
import br.com.project.domain.Pedido;
import br.com.project.domain.enums.EstadoPagamento;
import br.com.project.repositories.ItemPedidoRepository;
import br.com.project.repositories.PagamentoRepository;
import br.com.project.repositories.PedidoRepository;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository PedidoRepo;
	
	@Autowired
	private PagamentoRepository pgtoRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository ipRepo;
	
	public List<Pedido> buscarTodos() {
		return PedidoRepo.findAll();
	}
	
	public Pedido buscarPorId(Long id) {
		Pedido Pedido = PedidoRepo.findById(id)
				.orElseThrow( () -> new ObjectNotFoundException("Pedido não encontrado,"
						+ " para o id = " + id));
		return Pedido;		
	}

	@Transactional
	public  Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setData(new Date());
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getData());
			
		}
		
		obj = PedidoRepo.save(obj);
		pgtoRepo.save(obj.getPagamento());
		
		for(ItemPedido i: obj.getItens()) {
			i.setDesconto(0.0);
			i.setPreco(prodService.findById(i.getProduto().getId()).getPreco());
			i.setPedido(obj);			
		}
		ipRepo.saveAll(obj.getItens());		
		return obj;
	}

}
