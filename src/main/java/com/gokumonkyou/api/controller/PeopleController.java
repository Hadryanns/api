package com.gokumonkyou.api.controller;

import java.util.Optional;
import java.net.URI;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gokumonkyou.api.model.People;
import com.gokumonkyou.api.service.PeopleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/people")
public class PeopleController {

    private final PeopleService service;

    public PeopleController(PeopleService service) {
        this.service = service;
    }
    
    // Cadastrar
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody People newPeople) {
        People savedPeople = service.create(newPeople);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedPeople.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of(
                "people", savedPeople,
                "mensagem", "Pessoa adicionada com sucesso"));
    }

    // Buscar todas as pessoas
    @GetMapping()
    public ResponseEntity<?> findAll() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(404).body("Nenhuma pessoa encontrada.");
        }
        return ResponseEntity.ok(service.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<People> people = service.FindById(id);
        if (people.isPresent()) {
            return ResponseEntity.ok(people);

        } else {
            return ResponseEntity.status(404).body("Pessoa não encontrada");
        }
    }


    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody People people) {
        try {
            People updatedPeople = service.update(id, people);

            return ResponseEntity.ok(Map.of(
                    "people", updatedPeople,
                    "mensagem", "Pessoa alterada com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Pessoa não encontrada");
        }
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Pessoa removida com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Pessoa não encontrada");
        }
    }
}