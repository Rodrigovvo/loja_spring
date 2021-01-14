package com.dev.loja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dev.loja.repository.ProdutoRepository;

@Controller
public class PrincipalController {
	
	@Autowired
	ProdutoRepository produtoRepo;
	
    @GetMapping(value = {"/", " "})
    public ModelAndView acessarComprador(){
    	ModelAndView mv = new ModelAndView("/index");
    	mv.addObject("listaProdutos", produtoRepo.findAll());
        return mv;
    } 
    
}
