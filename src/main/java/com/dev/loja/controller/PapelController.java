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

import com.dev.loja.model.Papel;
import com.dev.loja.repository.PapelRepository;

@Controller
public class PapelController {

	@Autowired
	private PapelRepository papelRepo;

	@GetMapping("/papeis/cadastrar")
	public ModelAndView acessarCadastroPapel(Papel papel) {
		ModelAndView mv = new ModelAndView("administrativo/papeis/cadastro");
		mv.addObject("papel", papel);

		return mv;
	}

	@GetMapping("/papeis/editar/{id}")
	public ModelAndView editarPapel(@PathVariable("id") long id) {
		Papel papel = papelRepo.findById(id).get();
		return acessarCadastroPapel(papel);

	}

	@GetMapping("/papeis/remover/{id}")
	public ModelAndView removerPapel(@PathVariable("id") long id) {
		Optional<Papel> papel = papelRepo.findById(id);
		papelRepo.delete(papel.get());
		return acessarListaPapeis();

	}

	@PostMapping("/papeis/cadastrar")
	public ModelAndView salvarPapel(@Valid Papel papel, BindingResult resul) {
		if (resul.hasErrors()) {
			return acessarCadastroPapel(papel);
		} else {
			papelRepo.saveAndFlush(papel);
			return acessarCadastroPapel(new Papel());
		}
	}

	@GetMapping("/papeis/listar")
	public ModelAndView acessarListaPapeis() {
		ModelAndView mv = new ModelAndView("administrativo/papeis/lista");
		mv.addObject("listaPapeis", papelRepo.findAll());
		return mv;
	}
}
