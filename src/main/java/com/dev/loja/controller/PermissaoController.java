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

import com.dev.loja.model.Permissao;
import com.dev.loja.repository.FuncionarioRepository;
import com.dev.loja.repository.PapelRepository;
import com.dev.loja.repository.PermissaoRepository;

@Controller
public class PermissaoController {

	@Autowired
	private PermissaoRepository permissaoRepo;
	@Autowired
	private FuncionarioRepository funcionarioRepo;
	@Autowired
	private PapelRepository papelRepo;

	@GetMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView acessarCadastroPermissao(Permissao permissao) {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/cadastro");
		mv.addObject("permissao", permissao);
		mv.addObject("listaFuncionarios", funcionarioRepo.findAll());
		mv.addObject("listaPapeis", papelRepo.findAll());

		return mv;
	}

	@GetMapping("/administrativo/permissoes/editar/{id}")
	public ModelAndView editarPermissao(@PathVariable("id") long id) {
		Permissao permissao = permissaoRepo.findById(id).get();
		return acessarCadastroPermissao(permissao);

	}

	@GetMapping("/administrativo/permissoes/remover/{id}")
	public ModelAndView removerPermissao(@PathVariable("id") long id) {
		Optional<Permissao> permissao = permissaoRepo.findById(id);
		permissaoRepo.delete(permissao.get());
		return acessarListaPermissaos();

	}

	@PostMapping("/administrativo/permissoes/cadastrar")
	public ModelAndView salvarPermissao(@Valid Permissao permissao, BindingResult resul) {
		if (resul.hasErrors()) {
			return acessarCadastroPermissao(permissao);
		} else {
			permissaoRepo.saveAndFlush(permissao);
			return acessarCadastroPermissao(new Permissao());
		}
	}

	@GetMapping("/administrativo/permissoes/listar")
	public ModelAndView acessarListaPermissaos() {
		ModelAndView mv = new ModelAndView("administrativo/permissoes/lista");
		mv.addObject("listaPermissoes", permissaoRepo.findAll());
		return mv;
	}
}
