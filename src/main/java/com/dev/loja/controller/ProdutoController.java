package com.dev.loja.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.model.Produto;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class ProdutoController {

	private static final String IMAGE_URL = "/home/rodrigovvo/projects/loja_spring/src/main/resources/static/image/";

	@Autowired
	private ProdutoRepository produtoRepo;

	@GetMapping("/produtos/cadastrar")
	public ModelAndView acessarCadastroProduto(Produto produto) {
		ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
		mv.addObject("produto", produto);

		return mv;
	}

	@GetMapping("/produtos/editar/{id}")
	public ModelAndView editarProduto(@PathVariable("id") long id) {
		Produto produto = produtoRepo.findById(id).get();
		return acessarCadastroProduto(produto);

	}

	@GetMapping("/produtos/remover/{id}")
	public ModelAndView removerProduto(@PathVariable("id") long id) {
		Optional<Produto> produto = produtoRepo.findById(id);
		produtoRepo.delete(produto.get());
		return acessarListaProdutos();

	}

	@PostMapping("/produtos/cadastrar")
	public ModelAndView salvarProduto(@Valid Produto produto, BindingResult resul,
			@RequestParam("file") MultipartFile arquivo) {
		if (resul.hasErrors()) {
			return acessarCadastroProduto(produto);
		} else {
			produtoRepo.saveAndFlush(produto);
			try {
				if (!arquivo.isEmpty()) {
					byte[] bytes = arquivo.getBytes();
					String nomeImage = String.valueOf(produto.getId()) + arquivo.getOriginalFilename();
					Path caminho = Paths.get(IMAGE_URL + nomeImage);
					Files.write(caminho, bytes);

					produto.setNomeImagem(nomeImage);
					produtoRepo.saveAndFlush(produto);
				}

			} catch (Exception e) {
				e.printStackTrace();
				return acessarCadastroProduto(new Produto());
			}

			return acessarCadastroProduto(new Produto());
		}
	}

	// Listar os funcionários
	@GetMapping("/produtos/listar")
	public ModelAndView acessarListaProdutos() {
		ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
		mv.addObject("listaProdutos", produtoRepo.findAll());
		return mv;
	}

	@GetMapping("/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] mostrarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imageArquivo = new File(IMAGE_URL + imagem);
		if (imagem != null || imagem.trim().length() > 0) {
			return Files.readAllBytes(imageArquivo.toPath());
		} else {
			return null;
		}
	}
}
