package com.example.demo.mapper;

import com.example.demo.dto.PessoaDto;
import com.example.demo.entity.PessoaEntity;

public class PessoaMapper {

    public static PessoaEntity toEntity (PessoaDto dto){
        return PessoaEntity.builder()
                .name(dto.getName())
                .idade(dto.getIdade())
                .build();
    }

    public static PessoaDto toDto (PessoaEntity entity){
        return PessoaDto.builder()
                .name(entity.getName())
                .idade(entity.getIdade())
                .build();
    }
}
