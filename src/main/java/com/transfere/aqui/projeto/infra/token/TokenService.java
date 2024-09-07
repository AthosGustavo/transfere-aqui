package com.transfere.aqui.projeto.infra.token;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.transfere.aqui.projeto.excecoes.TokenExpiradoExcecao;


@Service
public class TokenService {
	
	
	public static final int TOKEN_EXPIRATION_MS = 15 * 60 * 1000;
	
	public static final String URL = "http://localhost:8080/arquivo/download?token="; 
	
	public Map<String, TokenInfo> mapTokenDownload = new HashMap<>();
	
	public String generateToken(Long idArquivo) {
		String token = UUID.randomUUID().toString(); //gera um token UUID
		Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_MS); //Define o tempo de expiração do token; tempo atual + os 15 minutos do token
		mapTokenDownload.put(token, new TokenInfo(idArquivo, expirationDate)); // Guarda o token e um objeto que possui o id e o tempo de expiração.
		return token;
	}
	
	public TokenInfo buscarToken(String token) {
		
		TokenInfo tokenInfo = mapTokenDownload.get(token);		
		
		if(new Date().compareTo(tokenInfo.getTempoExpiraToken()) == 1) {
			throw new TokenExpiradoExcecao(TokenExpiradoExcecao.TOKEN_EXPIRADO);
		}else {
			return tokenInfo;
		}
	}
	
}
