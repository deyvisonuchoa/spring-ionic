package br.com.project.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.project.domain.Categoria;
import br.com.project.domain.Cidade;
import br.com.project.domain.Cliente;
import br.com.project.domain.Endereco;
import br.com.project.domain.Estado;
import br.com.project.domain.Pagamento;
import br.com.project.domain.PagamentoComBoleto;
import br.com.project.domain.PagamentoComCartao;
import br.com.project.domain.Pedido;
import br.com.project.domain.Produto;
import br.com.project.domain.enums.EstadoPagamento;
import br.com.project.domain.enums.TipoCliente;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.repositories.CidadeRepository;
import br.com.project.repositories.ClienteRepository;
import br.com.project.repositories.EnderecoRepository;
import br.com.project.repositories.EstadoRepository;
import br.com.project.repositories.PagamentoRepository;
import br.com.project.repositories.PedidoRepository;
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

	@Autowired
	EnderecoRepository enderecoRepo;
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Autowired
	PedidoRepository pedidoRepo;
	
	@Autowired
	PagamentoRepository pagamentoRepo;
	
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
		
		Cliente cli1 = new Cliente(null, "deyvison uchoa", "deyvisonuchoa@gmqail.com", "11668953781", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("21969647700", "21985301484"));
		
		Endereco e1 = new Endereco(null, "rua caminho poder da boa vontade 72", "72", "", "guaratiba", "23020570", cli1, c1);
		Endereco e2 = new Endereco(null, "rua pajura", "245", "apto 201", "Taquara", "22740210", cli1, c1);

		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepo.save(cli1);
		enderecoRepo.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
//		Instant.parse("2017-09-30T10:32:00.00Z")
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);
		
		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pgto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepo.saveAll(Arrays.asList(pgto1,pgto2));
		
//		clienteRepo.save(entity)
	}

}
