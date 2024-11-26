package com.transfere.aqui.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transfere.aqui.projeto.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
