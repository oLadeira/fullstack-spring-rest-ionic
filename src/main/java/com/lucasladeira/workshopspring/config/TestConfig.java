package com.lucasladeira.workshopspring.config;



import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.repositories.CategoriaRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
				
		Categoria cat = new Categoria(null, "Informática");
		
		Categoria cat1 = new Categoria(null, "Móveis");
		
		categoriaRepository.saveAll(Arrays.asList(cat, cat1));
		
	}

}
