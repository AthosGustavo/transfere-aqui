package com.transfere.aqui.projeto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transfere.aqui.projeto.model.Arquivo;
import com.transfere.aqui.projeto.repository.ArquivoRepository;

@Service
public class ArquivoService {
	
	
	@Autowired
	private ArquivoRepository arquivoRepository;
	
	public ArquivoService() {}
	
	public Arquivo inserirArquivoService(Arquivo arquivo) {
		return this.arquivoRepository.save(arquivo);
	}

	public Arquivo buscarArquivoPorId(Long id) {
		return this.arquivoRepository.findById(id).orElse(null);
		
	} 
}
