package com.dev.loja.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.model.Entrada;
import com.dev.loja.model.EntradaItens;
import com.dev.loja.model.Produto;
import com.dev.loja.repository.EntradaItensRepository;
import com.dev.loja.repository.EntradaRepository;
import com.dev.loja.repository.FuncionarioRepository;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class EntradaController {

	public List<EntradaItens> listaEntradaItens = new ArrayList<>();

	@Autowired
	private EntradaRepository entradaRepo;

	@Autowired
	private EntradaItensRepository entradaItensRepo;

	@Autowired
	private FuncionarioRepository funcionarioRepo;

	@Autowired
	private ProdutoRepository produtoRepo;

	@GetMapping("/administrativo/entrada/cadastrar")
	public ModelAndView acessarEntradas(Entrada entrada, EntradaItens entradaItens) {
		ModelAndView mv = new ModelAndView("administrativo/entrada/cadastro");
		mv.addObject("listaEntradaItens", this.listaEntradaItens);
		mv.addObject("entrada", entrada);
		mv.addObject("entradaItens", entradaItens);
		mv.addObject("listaFuncionarios", funcionarioRepo.findAll());
		mv.addObject("listaProdutos", produtoRepo.findAll());

		return mv;
	}

	@PostMapping("/administrativo/entrada/cadastrar")
	public ModelAndView salvarEntrada(String acao, Entrada entrada, EntradaItens entradaItens) {

		switch (acao) {
		case "itens":
			this.listaEntradaItens.add(entradaItens);
			break;
		case "salvar":
			entradaRepo.saveAndFlush(entrada);
			for (EntradaItens it : this.listaEntradaItens) {
				it.setEntrada(entrada);
				entradaItensRepo.saveAndFlush(it);
				Produto produto = produtoRepo.findById(it.getProduto().getId()).get();
				produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + it.getQuantidade());
				produto.setValorVenda(it.getValorVenda());
				produtoRepo.saveAndFlush(produto);
			}
			this.listaEntradaItens = new ArrayList<EntradaItens>();
			return acessarEntradas(new Entrada(), new EntradaItens());

		default:
			break;
		}
		return acessarEntradas(entrada, new EntradaItens());

	}

	@GetMapping("/administrativo/entrada/listar")
	public ModelAndView acessarListaCidades() {
		ModelAndView mv = new ModelAndView("administrativo/entrada/lista");
		mv.addObject("listaEntrada", entradaRepo.findAll());
		return mv;
	}

}
