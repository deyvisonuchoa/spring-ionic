package br.com.project.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import br.com.project.domain.enums.Perfil;
import br.com.project.domain.enums.TipoCliente;
import br.com.project.domain.mestra.ClienteMestra;
import br.com.project.domain.mestra.FaseProjeto;
import br.com.project.domain.mestra.Metodologia;
import br.com.project.domain.mestra.OrdemFase;
import br.com.project.repositories.CategoriaRepository;
import br.com.project.repositories.CidadeRepository;
import br.com.project.repositories.ClienteRepository;
import br.com.project.repositories.EnderecoRepository;
import br.com.project.repositories.EstadoRepository;
import br.com.project.repositories.ItemPedidoRepository;
import br.com.project.repositories.PagamentoRepository;
import br.com.project.repositories.PedidoRepository;
import br.com.project.repositories.ProdutoRepository;
import br.com.project.repositories.mestra.ClienteMestraRepository;
import br.com.project.repositories.mestra.FaseProjetoRepository;
import br.com.project.repositories.mestra.MetodologiaRepository;
import br.com.project.repositories.mestra.OrdemFaseRepository;

@Service
public class DBService {
    
    @Autowired
    private BCryptPasswordEncoder encoder;
    
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
    
    @Autowired
    FaseProjetoRepository faseRepo;
    
    @Autowired
    ClienteMestraRepository cliRepo;

    @Autowired
    MetodologiaRepository metRepo;
    
    @Autowired
    OrdemFaseRepository ordemFasesRepo;
    
    public void instanciateTestDatabase() throws ParseException {
        
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
        
        Cliente cli1 = new Cliente(null, "deyvison uchoa", "deyvisonuchoa@gmail.com", "11668953781", TipoCliente.PESSOAFISICA, encoder.encode("123"));
        cli1.getTelefones().addAll(Arrays.asList("21969647700", "21985301484"));
        
        Cliente cli2 = new Cliente(null, "lorena ribeiro", "deyvisonuchoa@hotmail.com", "11668952700", TipoCliente.PESSOAFISICA, encoder.encode("123"));
        cli2.getTelefones().addAll(Arrays.asList("21954647700", "21989999484"));
        cli2.addPerfil(Perfil.ADMIN);
        
        Endereco e1 = new Endereco(null, "rua caminho poder da boa vontade 72", "72", "", "guaratiba", "23020570", cli1, c1);
        Endereco e2 = new Endereco(null, "rua pajura", "245", "apto 201", "Taquara", "22740210", cli1, c1);
        
        Endereco e3 = new Endereco(null, "rua da lore", "24225", "casa do lago dos jabuti", "Taquara", "22740210", cli2, c1);

        cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
        cli2.getEnderecos().addAll(Arrays.asList(e3));

        clienteRepo.saveAll(Arrays.asList(cli1, cli2));
        enderecoRepo.saveAll(Arrays.asList(e1, e2, e3));
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
//      Instant.parse("2017-09-30T10:32:00.00Z")
        
        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
        
        Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pgto1);
        
        Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPagamento(pgto2);
        
        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
        
        pedidoRepo.saveAll(Arrays.asList(ped1,ped2));
        pagamentoRepo.saveAll(Arrays.asList(pgto1,pgto2));
        
//      clienteRepo.save(entity)
        
        ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
        ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
        ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
        
        ped1.getItens().addAll(Arrays.asList(ip1,ip2));
        ped2.getItens().addAll(Arrays.asList(ip3));
        
        p1.getItens().addAll(Arrays.asList(ip1));
        p2.getItens().addAll(Arrays.asList(ip3));
        p3.getItens().addAll(Arrays.asList(ip2));
        
        itemPedidoRepo.saveAll(Arrays.asList(ip1,ip2,ip3));
        
        FaseProjeto fase = new FaseProjeto(null, "001", "d");
        FaseProjeto fase2 = new FaseProjeto(null, "002", "b");
        FaseProjeto fase3 = new FaseProjeto(null, "003", "a");
        FaseProjeto fase4 = new FaseProjeto(null, "004", "c");
        FaseProjeto fase5 = new FaseProjeto(null, "005", "l");
        FaseProjeto fase6 = new FaseProjeto(null, "006", "j");
        FaseProjeto fase7 = new FaseProjeto(null, "007", "q");
        FaseProjeto fase8 = new FaseProjeto(null, "008", "e");
        FaseProjeto fase9 = new FaseProjeto(null, "009", "y");
        FaseProjeto fase10 = new FaseProjeto(null, "010", "i");
        FaseProjeto fase11 = new FaseProjeto(null, "011", "f");
        FaseProjeto fase12 = new FaseProjeto(null, "012", "a");
        FaseProjeto fase13 = new FaseProjeto(null, "013", "ada");
        
        faseRepo.saveAll(Arrays.asList(fase,fase2,fase3,fase4,fase5,fase6,fase7,fase8,fase9,fase10,fase11, fase12, fase13));
        
        ClienteMestra clie = new ClienteMestra(null, "001", "Prevdata");
        ClienteMestra clie2 = new ClienteMestra(null, "002", "Faeces");
        ClienteMestra clie3 = new ClienteMestra(null, "003", "Nucleos");
        ClienteMestra clie4 = new ClienteMestra(null, "004", "Cifrão");
        ClienteMestra clie5 = new ClienteMestra(null, "005", "Prevmestra");
        ClienteMestra clie6 = new ClienteMestra(null, "006", "Telos");
        ClienteMestra clie7 = new ClienteMestra(null, "007", "Fucap");
        
        cliRepo.saveAll(Arrays.asList(clie, clie2, clie3, clie4, clie5, clie6, clie7));
        
        Metodologia met = new Metodologia(null, "001", "incremental");
        
        OrdemFase ordemFase = new OrdemFase(null, 1l, fase, met);
        OrdemFase ordemFase2 = new OrdemFase(null, 2l, fase4, met);
        OrdemFase ordemFase3 = new OrdemFase(null, 3l, fase8, met);
        
        metRepo.saveAll(Arrays.asList(met));
        ordemFasesRepo.saveAll(Arrays.asList(ordemFase, ordemFase2, ordemFase3));
    }
}
