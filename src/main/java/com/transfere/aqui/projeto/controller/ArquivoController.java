package com.transfere.aqui.projeto.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.transfere.aqui.projeto.infra.token.TokenInfo;
import com.transfere.aqui.projeto.infra.token.TokenService;
import com.transfere.aqui.projeto.model.Arquivo;
import com.transfere.aqui.projeto.service.ArquivoService;

import jakarta.annotation.Resource;




@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;
	
	@Autowired
	private TokenService tokenService;
		
	
	
	@PostMapping("/upload")
	public ResponseEntity<String> inserirArquivo(@RequestParam("file") MultipartFile dado) throws IOException {
		
		Arquivo arquivo = new Arquivo();
		arquivo.setNome(dado.getOriginalFilename());
		arquivo.setArquivoBinario(dado.getBytes());
		arquivo.setTipo(dado.getContentType());
		arquivo.setTamanho(dado.getSize());
		
		Arquivo arquivoSalvo = arquivoService.inserirArquivoService(arquivo);
		
		String token = tokenService.generateToken(arquivoSalvo.getId()); // token criptografado		
		String linkDownload = TokenService.URL+token;
		
		return ResponseEntity.status(HttpStatus.OK).body("Arquivo enviado com sucesso!\n Link para download:"+linkDownload); 
	}
	
	@GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam String token) {
	
		TokenInfo tokenInfo = tokenService.buscarToken(token);			
		Arquivo arquivo = arquivoService.buscarArquivoPorId(tokenInfo.getIdArquivo());

        ByteArrayInputStream bis = new ByteArrayInputStream(arquivo.getArquivoBinario());
        InputStreamResource resource = new InputStreamResource(bis);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(arquivo.getTamanho())
                .body(resource);
        
    }
	
	
	
	
}
