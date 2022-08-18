package com.carros.domain;

import com.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository rep;

    public List<CarroDTO> getCarros() {
        //List<Carro> carros = rep.findAll();
        //(linha do lambda sem contraction) List<CarroDTO> list =  carros.stream().map(c -> new CarroDTO(c)).collect(Collectors.toList());
        //List<CarroDTO> list =  carros.stream().map(CarroDTO::new).collect(Collectors.toList());
        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

        //return list;

        /*List<CarroDTO> list = new ArrayList<>();
        for (Carro c : carros) {
            list.add(new CarroDTO(c));
        }
        return list;*/
    }

    /*public Iterable<Carro> getCarros() {
        return rep.findAll();

    }*/
    public Optional<CarroDTO> getCarroById(Long id) {
        return rep.findById(id).map(CarroDTO::create);

        //Optional<Carro> carro = rep.findById(id);
        //return carro.map(value -> Optional.of(new CarroDTO(value))).orElse(null);



        /*Optional<Carro> carro = rep.findById(id);
        if (carro.isPresent()) {
            return  Optional.of(new CarroDTO(carro.get()));
        }
        else {
            return null;
        }*/

    }
    /* escondendo o método com construtor
    public List<Carro> getCarrosFake() {
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Fusca"));
        carros.add(new Carro(2L, "Brasilia"));
        carros.add(new Carro(3L, "Opala"));
        return carros;
    }*/

    //alterado devido o retorno 204
    //public Iterable<Carro> getCarroByTipo(String tipo) {
    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }

    public CarroDTO insert(Carro carro) {
        //Assert.notNull(carro.getId(),"Não foi possível inserir o registro");
        return CarroDTO.create(rep.save(carro));
    }

    /*

    public Carro insert(Carro carro) {
        return rep.save(carro);
    }
    public Carro update(Carro carro, Long id) {
        //Busca carro BD
        Optional<Carro> optional = getCarroById(id);
        if(optional.isPresent()) {
            Carro db = optional.get();
            //copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id: " + db.getId());
            rep.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi posssível atualizar o registro.");
        }

    }*/
    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        // Busca o carro no banco de dados
        Optional<Carro> optional = rep.findById(id);
        if (optional.isPresent()) {
            Carro db = optional.get();
            // Copiar as propriedades
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.println("Carro id " + db.getId());

            // Atualiza o carro
            rep.save(db);

            return CarroDTO.create(db);
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    /* public void delete(Long id) {
         Optional<CarroDTO> carro = getCarroById(id);
         if(carro.isPresent()) {
             rep.deleteById(id);
         }
     }*/
    public boolean delete(Long id) {
        if (getCarroById(id).isPresent()) {
            rep.deleteById(id);
            return true;
        }
        return false;
    }
}
