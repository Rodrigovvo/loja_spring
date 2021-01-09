package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.Funcionario;

@Repository
public interface EstadoRepository extends JpaRepository<Funcionario, Long>{

}
