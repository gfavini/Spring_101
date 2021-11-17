package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PessoaDto {
    private String name;
    private int idade;
}
