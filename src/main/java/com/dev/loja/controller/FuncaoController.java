package com.dev.loja.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.model.Funcao;
import com.dev.loja.repository.FuncaoRepository;

@Controller
public class FuncaoController {

	@Autowired
	private FuncaoRepository papelRepo;

	@GetMapping("/administrativo/papeis/cadastrar")
	public ModelAndView acessarCadastroPapel(Funcao funcao) {
		ModelAndView mv = new ModelAndView("administrativo/papeis/cadastro");
		mv.addObject("papel", funcao);

		return mv;
	}

	@GetMapping("/administrativo/papeis/editar/{id}")
	public ModelAndView editarPapel(@PathVariable("id") long id) {
		Funcao funcao = papelRepo.findById(id).get();
		return acessarCadastroPapel(funcao);

	}

	@GetMapping("/administrativo/papeis/remover/{id}")
	public ModelAndView removerPapel(@PathVariable("id") long id) {
		Optional<Funcao> funcao = papelRepo.findById(id);
		papelRepo.delete(funcao.get());
		return acessarListaPapeis();

	}

	@PostMapping("/administrativo/papeis/cadastrar")
	public ModelAndView salvarPapel(@Valid Funcao funcao, BindingResult resul) {
		if (resul.hasErrors()) {
			return acessarCadastroPapel(funcao);
		} else {
			papelRepo.saveAndFlush(funcao);
			return acessarCadastroPapel(new Funcao());
		}
	}

	@GetMapping("/administrativo/papeis/listar")
	public ModelAndView acessarListaPapeis() {
		ModelAndView mv = new ModelAndView("administrativo/papeis/lista");
		mv.addObject("listaPapeis", papelRepo.findAll());
		return mv;
	}
}
