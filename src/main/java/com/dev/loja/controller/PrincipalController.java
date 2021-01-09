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

import com.dev.loja.model.Funcionario;
import com.dev.loja.repository.FuncionarioRepository;

@Controller
public class PrincipalController {
	
	@Autowired
	private FuncionarioRepository funcionarioRepo;

    @GetMapping("/administrativo")
    public String acessarPrincipal(){
        return "administrativo/home";
    }
    
    
    //Cadastrar os funcionários

    @GetMapping("/funcionarios/cadastrar")
    public ModelAndView acessarCadastroUsucario (Funcionario funcionario){
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        
    	return mv;
    }
    
    @GetMapping("/funcionarios/editar/{id}")
    public ModelAndView editarFuncionario(@PathVariable("id") long id) {
		Optional<Funcionario> funcionario =  funcionarioRepo.findById(id);
    	return acessarCadastroUsucario(funcionario.get());
    	
    }
    
    @GetMapping("/funcionarios/remover/{id}")
    public ModelAndView removerFuncionario(@PathVariable("id") long id) {
    	Optional<Funcionario> funcionario =  funcionarioRepo.findById(id);
		funcionarioRepo.delete(funcionario.get());
    	return acessarListaUsucarios();
    	
    }
    
    
    @PostMapping("/funcionarios/cadastrar")
    public ModelAndView salvarUsuario(@Valid Funcionario funcionario, BindingResult resul) {
    	if(resul.hasErrors()) {
    		return acessarCadastroUsucario(funcionario);
    	}else {
    		funcionarioRepo.saveAndFlush(funcionario);
    		return acessarCadastroUsucario(new Funcionario());
    	}
    }
    
    
    // Listar os funcionários
    @GetMapping("/funcionarios/listar")
    public ModelAndView acessarListaUsucarios(){
    	ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
    	mv.addObject("listaFuncionarios", funcionarioRepo.findAll());
        return mv;
    }
}
