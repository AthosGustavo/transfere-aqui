package com.transfere.aqui.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transfere.aqui.projeto.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {

}
