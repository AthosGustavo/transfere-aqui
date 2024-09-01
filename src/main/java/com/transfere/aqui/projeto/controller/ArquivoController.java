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

import com.transfere.aqui.projeto.infra.token.TokenDownloadSemAutenticacao;
import com.transfere.aqui.projeto.model.Arquivo;
import com.transfere.aqui.projeto.service.ArquivoService;

import jakarta.annotation.Resource;




@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;	
	private Map<String, TokenDownloadSemAutenticacao> mapTokenDownload = new HashMap<>();	
	private static final int TOKEN_EXPIRATION_MS = 15 * 60 * 1000;
	
	@PostMapping("/upload")
	public ResponseEntity<String> inserirArquivo(@RequestParam("file") MultipartFile dado) throws IOException {
		
		Arquivo arquivo = new Arquivo();
		arquivo.setNome(dado.getOriginalFilename());
		arquivo.setArquivoBinario(dado.getBytes());
		arquivo.setTipo(dado.getContentType());
		arquivo.setTamanho(dado.getSize());
		
		Arquivo arquivoSalvo = arquivoService.inserirArquivoService(arquivo);
		
		String token = generateToken(arquivoSalvo.getId()); // token criptografado		
		String linkDownload = "http://localhost:8080/arquivo/download?token="+token;
		
		return ResponseEntity.status(HttpStatus.OK).body("Arquivo enviado com sucesso!\n Link para download:"+linkDownload); 
	}
	
	@GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam String token) {
	
		TokenDownloadSemAutenticacao tokenDownloadSemAutenticacao = mapTokenDownload.get(token);
		// O  token foi adicionado ao map junto com o valor do TokenData no momento do metodo post e devolvido como resposta
	    // Ao clicar no link deolvido, o token é capturado e pesquisado em tokenToFileIdMap.get(token), assim retornando TokenData		
		
		Arquivo arquivo = arquivoService.buscarArquivoPorId(tokenDownloadSemAutenticacao.getIdArquivo());

        ByteArrayInputStream bis = new ByteArrayInputStream(arquivo.getArquivoBinario());
        InputStreamResource resource = new InputStreamResource(bis);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(arquivo.getTamanho())
                .body(resource);
        
      //Tratar as seguintes exceções
		//Verificar se o token expirou
		//verificar se o arquivo é nulo
    }
	
	private String generateToken(Long idArquivo) {
		String token = Base64.getUrlEncoder().encodeToString((idArquivo + "-" + new SecureRandom().nextInt()).getBytes()); //gera um token em string único que é codificado em Base64, utilizando o fileId
		Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MS); //Define o tempo de expiração do token; tempo atual + os 15 minutos do token
		mapTokenDownload.put(token, new TokenDownloadSemAutenticacao(idArquivo, expirationDate)); // Guarda o token e um objeto que possui o id e o tempo de expiração.
		return token;
	}
	
	
}
