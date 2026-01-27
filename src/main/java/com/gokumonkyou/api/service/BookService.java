package com.gokumonkyou.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gokumonkyou.api.model.Book;
import com.gokumonkyou.api.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository){
        this.repository = repository;
    }

    //Criar novo livro
    public Book create(Book book){
        if (book.getIsbn() != null && repository.existsByIsbn(book.getIsbn())){
            throw new IllegalArgumentException("ISBN já cadastrado");

        }
        return repository.save(book);
    }

    //Busca todos os livros
    public List<Book> findAll(){
        return repository.findAll();
    }
            
    //Buscar por ID
    public Optional<Book> findById(Long id){
        return repository.findById(id);
    }

    //Atualizar livro existente
    public Book update(Long id,Book data){
        Book book = repository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Livro não encontrado"));

        if (data.getName() != null){
            book.setName(data.getName());
        }

        if (data.getAuthor() != null){
            book.setAuthor(data.getAuthor());
        }
        if (data.getIsbn() != null){
            book.setIsbn(data.getIsbn());
        }
        if (data.getLaunchDate() != null){
            book.setLaunchDate(data.getLaunchDate());
        }
        return repository.save(book);
            
    }

    //Deleta livro pelo ID
    public void delete(Long id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new IllegalArgumentException("Livro não encontrado");
        }
    }
}