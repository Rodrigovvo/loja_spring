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

import com.dev.loja.model.Produto;
import com.dev.loja.repository.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepo;

    
    //Cadastrar os funcionários

    @GetMapping("/produtos/cadastrar")
    public ModelAndView acessarCadastroProduto (Produto produto){
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
        mv.addObject("produto", produto);
        
    	return mv;
    }
    
    @GetMapping("/produtos/editar/{id}")
    public ModelAndView editarProduto(@PathVariable("id") long id) {
    	Produto produto =  produtoRepo.findById(id).get();
        return acessarCadastroProduto(produto);

    }
    
    @GetMapping("/produtos/remover/{id}")
    public ModelAndView removerProduto(@PathVariable("id") long id) {
    	Optional<Produto> produto =  produtoRepo.findById(id);
		produtoRepo.delete(produto.get());
    	return acessarListaProdutos();
    	
    }
    
    
    @PostMapping("/produtos/cadastrar")
    public ModelAndView salvarProduto(@Valid Produto produto, BindingResult resul) {
    	if(resul.hasErrors()) {
    		return acessarCadastroProduto(produto);
    	}else {
    		produtoRepo.saveAndFlush(produto);
    		return acessarCadastroProduto(new Produto());
    	}
    }
    
    
    // Listar os funcionários
    @GetMapping("/produtos/listar")
    public ModelAndView acessarListaProdutos(){
    	ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
    	mv.addObject("listaProdutos", produtoRepo.findAll());
        return mv;
    }
}
