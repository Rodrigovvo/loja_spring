package com.dev.loja.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImagemController {

	private static final String IMAGE_URL = "/home/rodrigovvo/projects/loja_spring/src/main/resources/static/image/";

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
