package com.transfere.aqui.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transfere.aqui.projeto.model.Pessoa;
import com.transfere.aqui.projeto.repository.PessoaRepository;
import com.transfere.aqui.projeto.service.PessoaService;

@RequestMapping("/cadastro")
@RestController
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	
	
	@PostMapping("/pessoa")	
	public void criarPessoa(@RequestBody Pessoa pessoa) {
		this.pessoaService.criarPessoa(pessoa);
		
	}
}
