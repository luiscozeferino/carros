package com.carros.api;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
    @Autowired
    private CarroService service;

    @GetMapping()
    //public ResponseEntity<Iterable<Carro>> get() {
    //public ResponseEntity<List<CarroDTO>> get() { com a tipo List DTO
    public ResponseEntity get() {
        return ResponseEntity.ok(service.getCarros());
        //return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCarroById(@PathVariable("id") Long id)
    {
        Optional<CarroDTO> carro = service.getCarroById(id);

        //lambida
        //return carro.map(c -> ResponseEntity.ok(c))
        return carro
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        //if ternario
        /*return carro.isPresent() ?
                ResponseEntity.ok(carro.get()) :
                ResponseEntity.notFound().build();
        */
        //if e else padrão
        /*if(carro.isPresent()) {
            return ResponseEntity.ok(carro.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }*/
    }
    @GetMapping("/tipo/{tipo}")
    //public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
    //public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = service.getCarrosByTipo(tipo);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }
    @PostMapping
    public ResponseEntity post(@RequestBody Carro carro) {
        try{
            CarroDTO c = service.insert(carro);
            URI location = getUri(c.getId());
            return ResponseEntity.created(null).build();
        }
        catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }
    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
    /*
     @PostMapping
    public String post(@RequestBody Carro carro) {
        Carro c = service.insert(carro);
        return "Carro salvo com sucesso:" + c.getId();
    }
    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
        Carro c = service.update(carro, id);
        return "Carro atualizado com sucesso: " + c.getId();
    }*/
    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
        carro.setId(id);
        CarroDTO c = service.update(carro, id);
        return c != null ?
                ResponseEntity.ok(c) :
                ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        boolean ok = service.delete(id);
        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }











    /*EXEMPLOS

@GetMapping
    public Iterable<Carro> get() {
        return service.getCarros();
    }
 @GetMapping("/{id}")
    public Optional<Carro> getCarroById(@PathVariable("id") Long id)
    {
        return service.getCarroById(id);
    }
    @GetMapping("/tipo/{tipo}")
    public Iterable<Carro> getCarroByTipo(@PathVariable("tipo") String tipo) {

        return service.getCarroByTipo(tipo);
    }

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
