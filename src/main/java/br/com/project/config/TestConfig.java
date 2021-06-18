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
import br.com.project.domain.ItemPedido;
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
import br.com.project.repositories.ItemPedidoRepository;
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
	
	@Autowired
	ItemPedidoRepository itemPedidoRepo;
	
	@Override
	public void run(String... args) throws Exception {		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");	
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "eletronicos");
		Categoria cat5 = new Categoria(null, "jardim");
		Categoria cat6 = new Categoria(null, "another one");
		Categoria cat7 = new Categoria(null, "and another one");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "mouse", 80.00);
		Produto p4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto p5 = new Produto(null, "Toalha", 50.00);
		Produto p6 = new Produto(null, "Colcha", 200.00);
		Produto p7 = new Produto(null, "TV true color", 1200.00);
		Produto p8 = new Produto(null, "Roçadeira", 800.00);
		Produto p9 = new Produto(null, "Abajour", 100.00);
		Produto p10 = new Produto(null, "Pendente", 180.00);
		Produto p11 = new Produto(null, "Shampoo", 90.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
				
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));	
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
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
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
