package com.transfere.aqui.projeto.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Table(schema = "public")
@Entity
@SequenceGenerator(name = "seq_arquivo", allocationSize = 1, initialValue = 1)
public class Arquivo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_arquivo")
	private Long id;
	private String nome;
	private String tipo;
	private Long tamanho;
	
    @Column(columnDefinition = "BYTEA")
	private byte[] arquivoBinario;
	
	@Temporal(TemporalType.DATE)
	private Date dtCarregamento = new Date();
	
	

	public Arquivo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getTamanho() {
		return tamanho;
	}

	public void setTamanho(Long tamanho) {
		this.tamanho = tamanho;
	}

	public Date getDtCarregamento() {
		return dtCarregamento;
	}

	public void setDtCarregamento(Date dtCarregamento) {
		this.dtCarregamento = dtCarregamento;
	}

	public byte[] getArquivoBinario() {
		return arquivoBinario;
	}

	public void setArquivoBinario(byte[] arquivoBinario) {
		this.arquivoBinario = arquivoBinario;
	}
	
	
}
