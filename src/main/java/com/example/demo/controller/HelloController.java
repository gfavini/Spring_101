package com.example.demo.controller;

import com.example.demo.entity.PessoaEntity;
import com.example.demo.services.HelloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HelloController {

    private final HelloService helloService; // Injeção do serviço

    @GetMapping("/hello")
    public String hello(){
        return "Hello Gustavo";
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return "Hello "+name+"!";

    }

    @GetMapping("/helloParam")
    public String helloParam(@RequestParam("name") String name){
        return "Hello "+name;
    }

    @GetMapping("/helloBody")
    public String helloBody(@RequestBody PessoaEntity pessoa){
        return "Nome: " + pessoa.getName()+" idade: "+pessoa.getIdade();
    }

    @GetMapping("/helloIdadeDias")
    public String helloIdadeDias(@RequestBody PessoaEntity pessoa){
        return helloService.userAndAgeInDays(pessoa);
    }

    @GetMapping("/helloIdadeDias/{name}")
    public String helloIdadeDias(@PathVariable("name") String name){
        return helloService.userAndAgeInDays(name);
    }

    @PostMapping("/pessoa")
    public PessoaEntity add(@RequestBody PessoaEntity pessoa){
        return helloService.addPessoa(pessoa);
    }

    @PutMapping("/pessoa/{name}")
    public PessoaEntity update (@RequestBody PessoaEntity pessoa, @PathVariable("name") String name){
        return helloService.update(pessoa, name);
    }

    @DeleteMapping("/pessoa/{name}")
    public void delete(@PathVariable("name") String name){
        helloService.delete(name);
    }


}
