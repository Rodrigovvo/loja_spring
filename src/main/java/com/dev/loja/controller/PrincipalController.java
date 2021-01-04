package com.dev.loja.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
    

    @GetMapping("/usuarios/cadastrar")
    public ModelAndView acessarCadastroUsucario (Funcionario funcionario){
        ModelAndView mv = new ModelAndView("administrativo/usuarios/cadastro");
        mv.addObject("funcionario", funcionario);
        
    	return mv;
    }
    @PostMapping("/usuarios/cadastrar")
    public ModelAndView salvarUsuario(@Valid Funcionario funcionario, BindingResult resul) {
    	if(resul.hasErrors()) {
    		return acessarCadastroUsucario(funcionario);
    	}else {
    		funcionarioRepo.save(funcionario);
    		return acessarCadastroUsucario(new Funcionario());
    	}
    }
    
    @GetMapping("/usuarios/listar")
    public String acessarListaUsucarios(){
        return "administrativo/usuarios/lista";
    }
}
