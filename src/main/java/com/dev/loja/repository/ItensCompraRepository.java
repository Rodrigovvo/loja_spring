package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.ItensCompra;

@Repository
public interface ItensCompraRepository extends JpaRepository<ItensCompra, Long>{

}
