package com.transfere.aqui.projeto.infra.token;

import java.util.Date;

import org.springframework.stereotype.Component;



public class TokenInfo{
	private Long idArquivo;
	private Date tempoExpiraToken;
	
	public TokenInfo(Long idArquivo, Date tempoExpiraToken) {
		super();
		this.idArquivo = idArquivo;
		this.tempoExpiraToken = tempoExpiraToken;
	}

	public Long getIdArquivo() {
		return idArquivo;
	}

	public void setIdArquivo(Long idArquivo) {
		this.idArquivo = idArquivo;
	}

	public Date getTempoExpiraToken() {
		return tempoExpiraToken;
	}

	public void setTempoExpiraToken(Date tempoExpiraToken) {
		this.tempoExpiraToken = tempoExpiraToken;
	}
	
	
	
}
