package com.transfere.aqui.projeto.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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

import com.transfere.aqui.projeto.model.Arquivo;
import com.transfere.aqui.projeto.service.ArquivoService;

import jakarta.annotation.Resource;




@RestController
@RequestMapping("/arquivo")
public class ArquivoController {
	
	@Autowired
	private ArquivoService arquivoService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> inserirArquivo(@RequestParam("file") MultipartFile dado) throws IOException {
		
		Arquivo arquivo = new Arquivo();
		arquivo.setNome(dado.getOriginalFilename());
		arquivo.setArquivoBinario(dado.getBytes());
		arquivo.setTipo(dado.getContentType());
		arquivo.setTamanho(dado.getSize());
		
		Arquivo arquivoSalvo = arquivoService.inserirArquivoService(arquivo);
		String linkDownload = "http://localhost:8080/arquivo/download/"+arquivo.getId();
		
		return ResponseEntity.status(HttpStatus.OK).body("Arquivo enviado com sucesso!\n Link para download:"+linkDownload); 
	}
	
	@GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> download(@PathVariable Long id) {
        Arquivo arquivo = arquivoService.buscarArquivoPorId(id);

        if (arquivo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(arquivo.getArquivoBinario());
        InputStreamResource resource = new InputStreamResource(bis);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNome() + "\"")
                .contentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(arquivo.getTamanho())
                .body(resource);
    }
}