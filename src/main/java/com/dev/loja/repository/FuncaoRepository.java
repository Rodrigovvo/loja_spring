package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.Funcao;

@Repository
public interface FuncaoRepository extends JpaRepository<Funcao, Long>{

}
