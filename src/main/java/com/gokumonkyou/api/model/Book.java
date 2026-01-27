package com.gokumonkyou.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

//Define a ordem das colunas na entidade/tabela, serve pra deixar o .json organizado.
@JsonPropertyOrder({"id", "name", "author", "isbn", "launchDate"})
@Entity
@Table(name = "book")
public class Book {
    
    //Colunas da tabela, preciso estudar mais sobre essas palavras reservadas depois.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name",nullable = true)
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "launchDate")
    private LocalDate launchDate;

    //Construtor necessário para API BootStrap
    public Book(){}
    

    //Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public LocalDate getLaunchDate() { return launchDate; }
    public void setLaunchDate(LocalDate launchDate) { this.launchDate = launchDate; }
}