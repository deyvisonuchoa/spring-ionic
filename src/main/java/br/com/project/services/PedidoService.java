package br.com.project.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.project.domain.Cliente;
import br.com.project.domain.ItemPedido;
import br.com.project.domain.PagamentoComBoleto;
import br.com.project.domain.Pedido;
import br.com.project.domain.enums.EstadoPagamento;
import br.com.project.repositories.ItemPedidoRepository;
import br.com.project.repositories.PagamentoRepository;
import br.com.project.repositories.PedidoRepository;
import br.com.project.security.UserDetail;
import br.com.project.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pgtoRepo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository ipRepo;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Pedido> buscarTodos() {
		return pedidoRepo.findAll();
	}
	
	public Page<Pedido> buscarTodosPaginados(Pageable pageable) {
	    UserDetail user = UserService.authenticated();
	    Cliente cliente = clienteService.buscarPorId(user.getId());
	    return pedidoRepo.findByCliente(cliente, pageable);
    }
	
	public Pedido buscarPorId(Long id) {
		Pedido Pedido = pedidoRepo.findById(id)
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
		
		obj = pedidoRepo.save(obj);
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
