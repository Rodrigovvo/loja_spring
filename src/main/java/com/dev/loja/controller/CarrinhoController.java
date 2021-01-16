package com.dev.loja.controller;

import java.util.LinkedHashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.model.ItensCompra;
import com.dev.loja.model.Produto;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class CarrinhoController {

	private LinkedHashSet<ItensCompra> itensCompra = new LinkedHashSet<>();

	@Autowired
	ProdutoRepository produtoRepo;

	@GetMapping("/carrinho")
	public ModelAndView acessoCarrinhoCompras() {
		ModelAndView mv = new ModelAndView("clientes/carrinho");
		mv.addObject("listaProdutos", itensCompra);
		return mv;
	}

	@GetMapping("/carrinho/adicionar/{id}")
	public ModelAndView adicionarItemCarrinhoCompras(@PathVariable long id) throws Exception {
		ModelAndView mv = new ModelAndView("clientes/carrinho");
		Optional<Produto> produto = produtoRepo.findById(id);
		ItensCompra ic = new ItensCompra();

		if (produto.isPresent()) {
			System.out.println("Produto é presente");
			ic.setProduto(produto.get());
			ic.setValorProduto(produto.get().getValorVenda());
			ic.setQuantidade(ic.getQuantidade() + 1);
			ic.setValorTotal(ic.getQuantidade() * ic.getValorProduto());
			this.itensCompra.add(ic);

		} else {
			throw new Exception("Error: Bad Request - Id do Produto não foi encontrado.");
		}

		System.out.println("Itens");
		mv.addObject("listaProdutos", itensCompra);
		return mv;
	}
}
