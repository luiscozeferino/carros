package com.carros.api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String get() {
        return "API dos carros.";
    }











    /*EXEMPLOS


    @PostMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha ) {
        return "Login: " + login + " Senha: " + senha;
    }


    @GetMapping("/login/{login}/senha/{senha}")
    public String login(@PathVariable("login") String login, @PathVariable("senha") String senha ) {
        return "Login: " + login + " Senha: " + senha;
    }


    @GetMapping("/carros/{id}")
    public String getCarroById(@PathVariable("id") String id) {
        return "Carro id: " + id;
    }
    @GetMapping("/carros/tipo/{tipo}")
    public String getCarroByTipo(@PathVariable("tipo") String tipo) {
        return "Lista carros tipo: " + tipo;
    }

    @GetMapping("/login")
    public String login(@RequestParam("login") String login, @RequestParam("senha") String senha ) {
        return "Login: " + login + " Senha: " + senha;
    }

    @PostMapping
    public String post() {
        return "post spring booti.";
    }
    @PutMapping
    public String put() {
        return "put spring booti.";
    }
    @DeleteMapping
    public String delete() {
        return "delete spring booti.";
    }*/
}
