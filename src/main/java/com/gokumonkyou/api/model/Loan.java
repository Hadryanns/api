package com.gokumonkyou.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//Define a ordem das colunas na entidade/tabela, serve pra deixar o .json organizado.
@JsonPropertyOrder({"id","loanDate","returnDate","returned","bookId","peopleId"})
@Entity
@Table(name = "loan")
public class Loan {
    
    //Colunas da tabela, preciso estudar mais sobre essas palavras reservadas depois.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "peopleId", nullable = false)
    private People people;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    private Book book;

    @Column(name = "loanDate")
    private LocalDate loanDate;

    @Column(name = "returnDate")
    private LocalDate returnDate;

    @Column(name = "returned", nullable = false)
    private boolean returned = false;

    //Construtor que o Spring utiliza.
    public Loan(){}

    //Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public People getPeople() { return people; }
    public void setPeople(People people) { this.people = people; }

    public LocalDate getLoanDate() { return loanDate; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public LocalDate getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }

    public Boolean getReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }
}