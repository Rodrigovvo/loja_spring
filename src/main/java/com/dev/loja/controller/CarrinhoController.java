package com.dev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarrinhoController {

	@GetMapping("/carrinho")
	public ModelAndView acessoCarrinhoCompras() {
		ModelAndView mv = new ModelAndView("clientes/carrinho");
		return mv;

	}
}
