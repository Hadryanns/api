package com.gokumonkyou.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gokumonkyou.api.model.Book;
import com.gokumonkyou.api.model.Loan;
public interface LoanRepository extends JpaRepository<Loan,Long> {
    boolean existsByBookAndReturnedFalse(Book book);
}