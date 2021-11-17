package com.example.demo.services;

import com.example.demo.dto.PessoaDto;
import com.example.demo.entity.PessoaEntity;
import com.example.demo.mapper.PessoaMapper;
import com.example.demo.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Autowired
    private HelloRepository helloRepository;

    public String userAndAgeInDays(PessoaEntity pessoa){
        return pessoa.getName() + "voce tem "+ pessoa.getIdade()*365 + " dias de vida";
    }

    public String userAndAgeInDays(String name) {
        PessoaEntity pessoa = helloRepository.findByName(name);
        return pessoa.getName() + "voce tem " + pessoa.getIdade() * 365 + " dias de vida";
    }

    public PessoaDto addPessoa(PessoaEntity pessoa) {
        return PessoaMapper.toDto(helloRepository.save(pessoa));
    }

    public PessoaEntity update(PessoaEntity pessoaUpdated, String name){
        PessoaEntity pessoa = helloRepository.findByName(name);
        pessoa.setName(pessoaUpdated.getName());
        pessoa.setIdade(pessoaUpdated.getIdade());

        return helloRepository.save(pessoa);
    }

    public void delete(String name) {
        PessoaEntity pessoa = helloRepository.findByName(name);
        helloRepository.delete(pessoa);
    }
}
