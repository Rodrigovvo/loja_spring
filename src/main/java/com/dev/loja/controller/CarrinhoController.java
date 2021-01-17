package com.dev.loja.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.model.ItensCompra;
import com.dev.loja.model.Produto;
import com.dev.loja.repository.ProdutoRepository;

import javassist.NotFoundException;

@Controller
public class CarrinhoController {

	private List<ItensCompra> itensCompra = new ArrayList<>();
	private String caminhoCarrinho = "clientes/carrinho";

	@Autowired
	ProdutoRepository produtoRepo;

	@GetMapping("/carrinho")
	public ModelAndView acessoCarrinhoCompras() {
		ModelAndView mv = new ModelAndView(caminhoCarrinho);
		mv.addObject("listaProdutos", itensCompra);
		return mv;
	}

	@GetMapping("/alterarQuantidade/{id}/{acao}")
	public String alterarQuantidade(@PathVariable Long id, @PathVariable int acao) throws NotFoundException{
		for (ItensCompra item : itensCompra) {
			if (item.getProduto().getId() == id) {
				switch (acao) {
				case 1:
					item.setQuantidade(item.getQuantidade() + 1);
					item.setValorTotal((item.getQuantidade() * item.getValorProduto()));
					
					return "redirect:/carrinho";
				case 2:
					if (item.getQuantidade() >= 1) {
						item.setQuantidade(item.getQuantidade() - 1);
						item.setValorTotal(item.getQuantidade() * item.getValorProduto());
						return "redirect:/carrinho";
					}
					else {
						return removerProdutoCarrinho(id);
						}
				default:
					break;
				}
			}else {
				throw new NotFoundException("Erro: Id do item não foi localizado"); 
			}
		}
		return "redirect:/carrinho";
	}
	
	

	@GetMapping("/removerProduto/{id}")
	public String removerProdutoCarrinho(@PathVariable Long id) {
		
		for (ItensCompra item : itensCompra) {
			if (id == item.getProduto().getId()) {
				itensCompra.remove(item);

				break;
			}
		}

		return "redirect:/carrinho" ;
	}
	
	@GetMapping("/carrinho/adicionar/{id}")
	public String adicionarItemCarrinhoCompras(@PathVariable long id) throws NotFoundException {
		Optional<Produto> produto = produtoRepo.findById(id);
		ItensCompra ic = new ItensCompra();

		if (produto.isPresent()) {
			var contador = 0;
			for (ItensCompra item : itensCompra) {
				if (item.getProduto().getId() == produto.get().getId()) {
					item.setQuantidade(item.getQuantidade() + 1);
					contador = 1;
					break;
				}
			}
			if (contador == 0) {
				ic.setProduto(produto.get());
				ic.setValorProduto(produto.get().getValorVenda());
				ic.setQuantidade(ic.getQuantidade() + 1);
				ic.setValorTotal(ic.getQuantidade() * ic.getValorProduto());
				itensCompra.add(ic);
			}
		} else {
			throw new NotFoundException("Error: Bad Request - Id do Produto não foi encontrado.");
		}
		return "redirect:/carrinho";
	}
}
