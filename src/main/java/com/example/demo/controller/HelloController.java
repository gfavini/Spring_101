package com.example.demo.controller;

import com.example.demo.dto.PessoaDto;
import com.example.demo.entity.PessoaEntity;
import com.example.demo.response.BaseResponse;
import com.example.demo.services.HelloService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import javax.servlet.http.HttpServletResponse;


@RestController
//@AllArgsConstructor
public class HelloController {

    private final HelloService helloService; // Injeção do serviço

    private final Counter counter = Metrics.counter("hello_resqust_counter", "uri", "/api/users");;

    @Autowired
    final PrometheusMeterRegistry prometheusRegistry = new PrometheusMeterRegistry(PrometheusConfig.DEFAULT);

    public HelloController(HelloService helloService){

        this.helloService = helloService;

    }




    // Cria um endpoint para o protheus sem as informações do actuator do spring
    @GetMapping("/prometheus")
    public String prometheus(){

        String response = prometheusRegistry.scrape();
        System.out.println(response);
        return response;

    }

    @GetMapping("/hello")
    public String hello() throws InterruptedException {

         counter.increment();
        int random_int = (int)Math.floor(Math.random()*(700-200+1)+200);
        TimeUnit.MILLISECONDS.sleep(random_int);

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
    public BaseResponse<PessoaDto> add(@RequestBody PessoaEntity pessoa){
        return BaseResponse.<PessoaDto>builder()
                .httpCode(200)
                .message("OK")
                .response(helloService.addPessoa(pessoa))
                .build();
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
