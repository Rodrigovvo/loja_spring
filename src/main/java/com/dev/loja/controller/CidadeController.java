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

import com.dev.loja.model.Cidade;
import com.dev.loja.repository.CidadeRepository;
import com.dev.loja.repository.EstadoRepository;

@Controller
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;

    @GetMapping("/cidades/cadastrar")
    public ModelAndView acessarCadastroCidade (Cidade cidade){
        ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
        mv.addObject("listaEstados", estadoRepo.findAll());
        mv.addObject("cidade", cidade);
        
    	return mv;
    }
    
    @GetMapping("/cidades/editar/{id}")
    public ModelAndView editarCidade(@PathVariable("id") long id) {
    	Cidade cidade =  cidadeRepo.findById(id).get();
        return acessarCadastroCidade(cidade);

    }
    
    @GetMapping("/cidades/remover/{id}")
    public ModelAndView removerCidade(@PathVariable("id") long id) {
    	Optional<Cidade> cidade =  cidadeRepo.findById(id);
		cidadeRepo.delete(cidade.get());
    	return acessarListaCidades();
    	
    }
    
    
    @PostMapping("/cidades/cadastrar")
    public ModelAndView salvarCidade(@Valid Cidade cidade, BindingResult resul) {
    	if(resul.hasErrors()) {
    		return acessarCadastroCidade(cidade);
    	}else {
    		cidadeRepo.saveAndFlush(cidade);
    		return acessarCadastroCidade(new Cidade());
    	}
    }
    
    
    // Listar os funcionários
    @GetMapping("/cidades/listar")
    public ModelAndView acessarListaCidades(){
    	ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
    	mv.addObject("listaCidades", cidadeRepo.findAll());
        return mv;
    }
}
