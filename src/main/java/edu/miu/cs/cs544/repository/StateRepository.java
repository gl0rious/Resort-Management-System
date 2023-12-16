package edu.miu.cs.cs544.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.cs.cs544.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {
    State findById(int id);

    Optional<State> findByCode(String code);
}
