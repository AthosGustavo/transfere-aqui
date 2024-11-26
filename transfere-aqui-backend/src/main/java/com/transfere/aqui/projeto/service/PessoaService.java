package com.transfere.aqui.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.transfere.aqui.projeto.model.Pessoa;
import com.transfere.aqui.projeto.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Pessoa criarPessoa(Pessoa pessoa) {	
		return this.pessoaRepository.save(pessoa);
	}
}
