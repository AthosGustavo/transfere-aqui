package com.transfere.aqui.projeto.model;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.ForeignKey;

@Table(schema = "public", uniqueConstraints = @UniqueConstraint(name = "uc_pessoa_id", columnNames = "pessoa_id"))
@Entity
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class Usuario {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	private Long id;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String senha;
	
	@OneToOne
	@JoinColumn(
		name = "pessoa_id",
		foreignKey = @ForeignKey(name = "pessoa_fk", value = ConstraintMode.CONSTRAINT))
	private Pessoa pessoa;
	
	@ManyToMany
	@JoinTable(
		name = "usuarios_acessos",
		joinColumns = @JoinColumn(name = "usuario_id",foreignKey = @ForeignKey(name = "usuario_fk", value = ConstraintMode.CONSTRAINT)),
		inverseJoinColumns = @JoinColumn(name = "acesso_id",foreignKey = @ForeignKey(name = "acesso_fk", value = ConstraintMode.CONSTRAINT)))
	private List<Acesso> acessos;
	
	
}
