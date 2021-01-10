package com.dev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.loja.model.Papel;

@Repository
public interface PapelRepository extends JpaRepository<Papel, Long>{

}
