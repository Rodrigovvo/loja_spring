package com.dev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetalheProdutoController {

	@GetMapping("/detalhe")
	public ModelAndView acessoNegado() {
		return new ModelAndView("/clientes/detalheProduto");
	}

}