package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.EntradaItens;

@Repository
public interface EntradaItensRepository extends JpaRepository<EntradaItens, Long>{

}
