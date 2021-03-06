package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Long>{

}
