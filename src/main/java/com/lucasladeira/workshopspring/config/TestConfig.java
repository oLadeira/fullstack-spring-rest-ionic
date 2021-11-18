package com.lucasladeira.workshopspring.config;



import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.domain.Cidade;
import com.lucasladeira.workshopspring.domain.Cliente;
import com.lucasladeira.workshopspring.domain.Endereco;
import com.lucasladeira.workshopspring.domain.Estado;
import com.lucasladeira.workshopspring.domain.ItemPedido;
import com.lucasladeira.workshopspring.domain.Pagamento;
import com.lucasladeira.workshopspring.domain.PagamentoComBoleto;
import com.lucasladeira.workshopspring.domain.PagamentoComCartao;
import com.lucasladeira.workshopspring.domain.Pedido;
import com.lucasladeira.workshopspring.domain.Produto;
import com.lucasladeira.workshopspring.domain.enums.EstadoPagamento;
import com.lucasladeira.workshopspring.domain.enums.TipoCliente;
import com.lucasladeira.workshopspring.repositories.CategoriaRepository;
import com.lucasladeira.workshopspring.repositories.CidadeRepository;
import com.lucasladeira.workshopspring.repositories.ClienteRepository;
import com.lucasladeira.workshopspring.repositories.EnderecoRepository;
import com.lucasladeira.workshopspring.repositories.EstadoRepository;
import com.lucasladeira.workshopspring.repositories.ItemPedidoRepository;
import com.lucasladeira.workshopspring.repositories.PagamentoRepository;
import com.lucasladeira.workshopspring.repositories.PedidoRepository;
import com.lucasladeira.workshopspring.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		//instanciando e salvando os objetos no banco h2 
		Categoria cat1 = new Categoria(null, "Informática");		
		Categoria cat2 = new Categoria(null, "Escritório");		
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
						
			
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		
		Cidade cid1 = new Cidade(null, "São Paulo", est2);
		Cidade cid2 = new Cidade(null, "Uberlândia", est1);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		
		est1.getCidades().addAll(Arrays.asList(cid2));
		est2.getCidades().addAll(Arrays.asList(cid1, cid3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("2763323", "9383893"));
		
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, cid2);		
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", cli1, cid1);
		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
