package com.gokumonkyou.api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.GenerationType;


//Define a ordem das colunas na entidade/tabela, serve pra deixar o .json organizado.
@JsonPropertyOrder({"id", "name", "email", "cpf", "phone", "birthdate"})
@Entity
@Table(name = "people")
public class People {
    
    //Colunas da tabela, preciso estudar mais sobre essas palavras reservadas depois.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name="cpf", unique = true, length = 14)
    private String cpf;

    @Column(name = "phone")
    private String phone;

    @Email
    @Column(name = "email")
    private String email;

    //Construtor necessário para API BootStrap
    public People(){}

    //Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public LocalDate getBirthdate() { return birthdate; }
    public void setBirthdate(LocalDate birthdate) { this.birthdate = birthdate; }
    
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}