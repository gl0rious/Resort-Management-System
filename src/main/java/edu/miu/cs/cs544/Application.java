package edu.miu.cs.cs544;

import edu.miu.cs.cs544.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.repository.CountryRepository;
import edu.miu.cs.cs544.repository.StateRepository;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	StateRepository stateRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {

		// add 10 countries
		Country US = countryRepository.save(new Country("US", "United States", 1000000));
		countryRepository.save(new Country("CA", "Canada", 500000));
		countryRepository.save(new Country("MX", "Mexico", 200000));
		countryRepository.save(new Country("GB", "United Kingdom", 100000));
		countryRepository.save(new Country("FR", "France", 200000));
		countryRepository.save(new Country("DE", "Germany", 300000));
		countryRepository.save(new Country("IT", "Italy", 400000));
		countryRepository.save(new Country("ES", "Spain", 500000));
		countryRepository.save(new Country("JP", "Japan", 600000));
		countryRepository.save(new Country("CN", "China", 700000));

		State state = new State("CA", "California");
		state.setCountry(US);
		stateRepository.save(state);

		state = new State("TX", "Texas");
		state.setCountry(US);
		stateRepository.save(state);

		state = new State("FL", "Florida");
		state.setCountry(US);
		stateRepository.save(state);

		state = new State("NY", "New York");
		state.setCountry(US);
		stateRepository.save(state);

	}

}
