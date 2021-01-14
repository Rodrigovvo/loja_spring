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
public class ImagemController {

	private static final String IMAGE_URL = "/home/rodrigovvo/projects/loja_spring/src/main/resources/static/image/";

	@Autowired
	private ProdutoRepository produtoRepo;


	@GetMapping("/produtos/mostrarImagem/{imagem}")
	@ResponseBody
	public byte[] mostrarImagem(@PathVariable("imagem") String imagem) throws IOException {
		File imageArquivo = new File(IMAGE_URL + imagem);
		byte[] nulo = null;
		if (imagem != null) {
			if(imagem.trim().length() > 0) 
				return Files.readAllBytes(imageArquivo.toPath());
		} else {
			return nulo;
		}
		return nulo;
	}
}
