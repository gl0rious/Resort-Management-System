package edu.miu.cs.cs544.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.miu.cs.cs544.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}
