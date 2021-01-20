package com.dev.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioLoginController {

	@GetMapping("/cliente/cadastro/novousuario")
	public ModelAndView novoClienteCadatrar() {
		return new ModelAndView("clientes/novo-usuario");
	}

	
	@GetMapping("/cliente/login")
	public ModelAndView loginClienteCadastrado() {
		return new ModelAndView("clientes/login-usuario");
	}
}
