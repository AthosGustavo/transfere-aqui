package com.transfere.aqui.projeto.excecoes;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControladorExcecoes {
	
	@ExceptionHandler(TokenExpiradoExcecao.class)
	public ResponseEntity<Map<String, String>> handleTokenExpiradoExcecao(TokenExpiradoExcecao ex) {
	    Map<String, String> erroDetalhado = new HashMap<>();
	    erroDetalhado.put("mensagem", ex.getMessage());
	    erroDetalhado.put("codigo", String.valueOf(HttpStatus.BAD_REQUEST.value())); // Código HTTP 400
	    return new ResponseEntity<>(erroDetalhado, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratamentoGenericoExcecao(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro inesperado:" + ex.getMessage());
	}
}
