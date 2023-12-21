package edu.miu.cs.cs544.repository;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAllByCustomer(Customer customer);

    boolean existsById(int id);
}