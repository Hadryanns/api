package com.gokumonkyou.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gokumonkyou.api.model.People;
import java.util.Optional;
public interface PeopleRepository extends JpaRepository<People,Long> {
    Optional<People> findByCpf(String cpf);
    boolean existsByCpf(String cpf);
}