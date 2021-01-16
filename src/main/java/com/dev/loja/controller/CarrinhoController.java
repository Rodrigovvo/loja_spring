package com.dev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoController {

	@GetMapping("/carrinho")
	public ModelAndView acessoCarrinhoCompras() {
		return new ModelAndView("clientes/carrinho");
	}
	
	@GetMapping("/carrinho/adicionar/{id}")
	public ModelAndView adicionarItemCarrinhoCompras(@PathVariable long id) {
		System.out.println(id);
		return new ModelAndView("clientes/carrinho");
	}
}
