package com.carros.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CRUD(CRIAR/RESTAURAR/ATUALIZAR/EXCLUIR)
//public interface CarroRepository extends CrudRepository<Carro, Long> {
public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByTipo(String tipo);
    //foi alterado devido o tratamento do 204
    //Iterable<Carro> findByTipo(String tipo);
}
