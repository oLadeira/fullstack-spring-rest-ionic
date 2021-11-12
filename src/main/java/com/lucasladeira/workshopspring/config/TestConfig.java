package com.lucasladeira.workshopspring.config;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.domain.Cidade;
import com.lucasladeira.workshopspring.domain.Estado;
import com.lucasladeira.workshopspring.domain.Produto;
import com.lucasladeira.workshopspring.repositories.CategoriaRepository;
import com.lucasladeira.workshopspring.repositories.CidadeRepository;
import com.lucasladeira.workshopspring.repositories.EstadoRepository;
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
	
	
	@Override
	public void run(String... args) throws Exception {
		
		//instanciando e salvando os objetos no banco h2 
		Categoria cat1 = new Categoria(null, "Informática");		
		Categoria cat2 = new Categoria(null, "Escritório");		
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
						
			
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
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
	}

}
