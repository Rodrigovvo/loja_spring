package com.dev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetalheProdutoController {

	@GetMapping("/detalhe/{id}")
	public ModelAndView acessoNegado(@PathVariable Long id) {
		return new ModelAndView("clientes/detalheProduto");
	}

}
