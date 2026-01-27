package com.gokumonkyou.api.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gokumonkyou.api.DTOs.LoanUpdateDTO;
import com.gokumonkyou.api.model.Book;
import com.gokumonkyou.api.model.Loan;
import com.gokumonkyou.api.model.People;
import com.gokumonkyou.api.repository.BookRepository;
import com.gokumonkyou.api.repository.LoanRepository;
import com.gokumonkyou.api.repository.PeopleRepository;

@Service
public class LoanService {

    // Instanciando todas as entidades que estão relacionas com o empréstimo
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final PeopleRepository peopleRepository;

    public LoanService(LoanRepository loanRepository, BookRepository bookRepository,
            PeopleRepository peopleRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.peopleRepository = peopleRepository;
    }

    // Criar empréstimo
    public Loan create(Long peopleId, Long bookId) {
        People people = peopleRepository.findById(peopleId)
                .orElseThrow(() ->  new IllegalArgumentException("Pessoa não encontrada Teste AAA"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado Teste AAA"));

        // Entender como adicionar métodos personalizados no repository do Spring
        if (loanRepository.existsByBookAndReturnedFalse(book)) {
            throw new IllegalArgumentException("Livro já emprestado");
        }

        Loan loan = new Loan();
        loan.setPeople(people);
        loan.setBook(book);
        loan.setLoanDate(LocalDate.now());
        loan.setReturned(false);

        return loanRepository.save(loan);
    }

    // Buscar todos os empréstimos
    public List<Loan> findAll() {
        return loanRepository.findAll();
    }

    // Buscar por ID
    public Optional<Loan> findById(Long id) {
        if (loanRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID não existe");
        } else {
            return loanRepository.findById(id);
        }
    }

    // Atualizar empréstimo existente
    public Loan update(Long id, LoanUpdateDTO dto) {
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Empréstimo não encontrado"));

        loan.setReturned(dto.isReturned());
        loan.setReturnDate(dto.getReturnDate());

        return loanRepository.save(loan);
    }

    // Deleta empréstimo pelo ID
    public void delete(Long id) {
        if (loanRepository.findById(id).isPresent()) {
            loanRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Empréstimo não encontrado");
        }
    }
}