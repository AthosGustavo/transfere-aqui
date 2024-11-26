package com.transfere.aqui.projeto.excecoes;

public class TokenExpiradoExcecao extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public static final String TOKEN_EXPIRADO = "Token expirado";
	
	public TokenExpiradoExcecao(String mensagem) {
		super(mensagem);
	}
}
