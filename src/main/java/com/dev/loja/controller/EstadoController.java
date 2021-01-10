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

import com.dev.loja.model.Estado;
import com.dev.loja.repository.EstadoRepository;

@Controller
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepo;


    @GetMapping("/estados/cadastrar")
    public ModelAndView acessarCadastroEstado (Estado estado){
        ModelAndView mv = new ModelAndView("administrativo/estados/cadastro");
        mv.addObject("estado", estado);
        
    	return mv;
    }
    
    @GetMapping("/estados/editar/{id}")
    public ModelAndView editarEstado(@PathVariable("id") long id) {
    	Estado estado =  estadoRepo.findById(id).get();
        return acessarCadastroEstado(estado);

    }
    
    @GetMapping("/estados/remover/{id}")
    public ModelAndView removerEstado(@PathVariable("id") long id) {
    	Optional<Estado> estado =  estadoRepo.findById(id);
		estadoRepo.delete(estado.get());
    	return acessarListaEstados();
    	
    }
    
    
    @PostMapping("/estados/cadastrar")
    public ModelAndView salvarEstado(@Valid Estado estado, BindingResult resul) {
    	if(resul.hasErrors()) {
    		return acessarCadastroEstado(estado);
    	}else {
    		estadoRepo.saveAndFlush(estado);
    		return acessarCadastroEstado(new Estado());
    	}
    }
    
    
    // Listar os funcionários
    @GetMapping("/estados/listar")
    public ModelAndView acessarListaEstados(){
    	ModelAndView mv = new ModelAndView("administrativo/estados/lista");
    	mv.addObject("listaEstados", estadoRepo.findAll());
        return mv;
    }
}
