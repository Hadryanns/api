package com.gokumonkyou.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gokumonkyou.api.model.People;
import com.gokumonkyou.api.repository.PeopleRepository;


@Service
public class PeopleService {
    private final PeopleRepository repository;

    public PeopleService(PeopleRepository repository){
        this.repository = repository;
    }


    //Criar nova pessoa
    public People create(People people){
        if (people.getCpf() != null && repository.existsByCpf(people.getCpf())){
            throw new IllegalArgumentException("CPF já cadastrado");

        }
        return repository.save(people);
    }

    //Busca todas as pessoas
    public List<People> findAll(){
        return repository.findAll();
    }
            
    //Buscar por ID
    public Optional<People> FindById(Long id){
        return repository.findById(id);
    }

    //Atualizar pessoa existente
    public People update(Long id,People data){
        People people = repository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Pessoa não encontrada"));

        if (data.getName() != null){
            people.setName(data.getName());
        }

        if (data.getEmail() != null){
            people.setEmail(data.getEmail());
        }
        if (data.getPhone() != null){
            people.setPhone(data.getPhone());
        }
        if (data.getBirthdate() != null){
            people.setPhone(data.getPhone());
        }
        if (data.getCpf() != null){
            people.setCpf(data.getCpf());
        }
        return repository.save(people);
            
    }

    //Deleta pessoa por ID
    public void delete(Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Pessoa não encontrada");
        }
    }
}