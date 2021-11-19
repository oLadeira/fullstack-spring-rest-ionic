package com.lucasladeira.workshopspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.Categoria;
import com.lucasladeira.workshopspring.dto.CategoriaDTO;
import com.lucasladeira.workshopspring.repositories.CategoriaRepository;
import com.lucasladeira.workshopspring.services.exceptions.DataIntegrityException;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public List<Categoria> findAll(){
		return categoriaRepository.findAll();
	}
	
	public Optional<Categoria> findById(Integer id){		
		Optional<Categoria> opt = categoriaRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new ObjectNotFoundException("Categoria não encontrada! ID: " + id);
		}
		
		return opt;
	}
	
	//Page - encapsula informacoes sobre a paginacao, retorna uma pagina com os dados
									//qual pagina eu quero, quantas linhas por pagina eu quero, ordenar por qual atributo, direcao ascendente ou descendente
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}
	
	public Categoria update (Integer id, Categoria categoria) {
		
		Optional<Categoria> opt = categoriaRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Categoria não encontrada!");
		}
		
		Categoria databaseCategoria = opt.get();
		
		updateData(databaseCategoria, categoria);
		
		return categoriaRepository.save(databaseCategoria);
	}
	
	public void delete (Integer id) {
		Optional<Categoria> opt = categoriaRepository.findById(id);
		
		if(opt.isEmpty()) {
			throw new ObjectNotFoundException("Categoria não encontrada! ID: " + id);
		}
		try {
			categoriaRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
	private void updateData(Categoria databaseCategoria, Categoria categoria) {
		databaseCategoria.setNome(categoria.getNome());
	}
}
