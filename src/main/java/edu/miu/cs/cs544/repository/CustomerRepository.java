package edu.miu.cs.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.cs.cs544.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
