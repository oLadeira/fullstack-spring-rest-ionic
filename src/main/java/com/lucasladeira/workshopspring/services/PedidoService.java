package com.lucasladeira.workshopspring.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasladeira.workshopspring.domain.ItemPedido;
import com.lucasladeira.workshopspring.domain.PagamentoComBoleto;
import com.lucasladeira.workshopspring.domain.Pedido;
import com.lucasladeira.workshopspring.domain.enums.EstadoPagamento;
import com.lucasladeira.workshopspring.repositories.ItemPedidoRepository;
import com.lucasladeira.workshopspring.repositories.PagamentoRepository;
import com.lucasladeira.workshopspring.repositories.PedidoRepository;
import com.lucasladeira.workshopspring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private EmailService emailService;
	
	public List<Pedido> findAll(){
		return pedidoRepository.findAll();
	}
	
	public Optional<Pedido> findById(Integer id){
		Optional<Pedido> opt = pedidoRepository.findById(id);
		
		if (opt.isEmpty()) {
			throw new ObjectNotFoundException("Pedido não encontrado!");
		}
		return opt;
	}
	
	public Pedido insert (Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.findById(pedido.getCliente().getId()).get());
		pedido.setEnderecoEntrega(enderecoService.findById(pedido.getEnderecoEntrega().getId()).get());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = pedidoRepository.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()).get());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		emailService.sendOrderConfirmationEmail(pedido);
		return pedido;
	}
}
