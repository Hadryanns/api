package com.gokumonkyou.api.controller;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

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

import com.gokumonkyou.api.DTOs.LoanRequestDTO;
import com.gokumonkyou.api.DTOs.LoanUpdateDTO;
import com.gokumonkyou.api.model.Loan;
import com.gokumonkyou.api.service.LoanService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/loan")
public class LoanController {

    private final LoanService service;

    public LoanController(LoanService service) {
        this.service = service;
    }

    // Buscar todos os empréstimos
    @GetMapping()
    public ResponseEntity<?> findAll() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.ok("Nenhum empréstimo encontrado");
        }
        return ResponseEntity.ok(service.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Loan>> findById(@PathVariable Long id) {
        Optional<Loan> loan = service.findById(id);
        if (loan != null) {
            return ResponseEntity.ok(loan);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cadastrar
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody LoanRequestDTO dto) {
        Loan savedLoan = service.create(dto.getCpf(), dto.getIsbn(), dto.getReturnDate());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedLoan.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of(
                "loan", savedLoan,
                "mensagem", "Emprétimo criado com sucesso"));
    }

    // Atualizar empréstimo
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody LoanUpdateDTO dto) {
        try {
            Loan updatedLoan = service.update(id, dto);

            return ResponseEntity.ok(Map.of(
                    "loan", updatedLoan,
                    "mensagem", "Empréstimo atualizado com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Empréstimo não encontrado");
        }
    }

    // Deletar empréstimo
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Empréstimo removido com sucesso");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Empréstimo não encontrado");
        }
    }
}