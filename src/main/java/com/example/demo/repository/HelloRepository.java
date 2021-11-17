package com.example.demo.repository;

import com.example.demo.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<PessoaEntity, Integer> {
    PessoaEntity findByName(String name); // Cria busca por nome
}
