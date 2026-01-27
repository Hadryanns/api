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

import com.gokumonkyou.api.model.Book;
import com.gokumonkyou.api.service.BookService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService service;

    public BookController(BookService service){
        this.service = service;
    }

    // Buscar todos os livros
    @GetMapping()
    public ResponseEntity<?> findAll() {
        if (service.findAll().isEmpty()) {
            return ResponseEntity.status(404).body("Nenhum livro encontrado");
        }
        return ResponseEntity.ok(service.findAll());
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Book> book = service.findById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book);

        } else {
            return ResponseEntity.status(404).body("Livro não encontrado");
        }
    }

    // Cadastrar
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Book newBook) {
        Book savedBook = service.create(newBook);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedBook.getId())
                .toUri();

        return ResponseEntity.created(location).body(Map.of(
                "book", savedBook,
                "mensagem", "Livro adicionado com sucesso"));
    }

    // Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        try {
            Book updatedBook = service.update(id, book);

            return ResponseEntity.ok(Map.of(
                    "book", updatedBook,
                    "mensagem", "Livro alterado com sucesso"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("Livro não encontrado");
        }
    }

    // Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok("Livro removido com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Livro não encontrado");
        }
    }
}