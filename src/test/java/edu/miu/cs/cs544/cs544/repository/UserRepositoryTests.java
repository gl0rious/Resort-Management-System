package edu.miu.cs.cs544.cs544.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.domain.UserType;
import edu.miu.cs.cs544.repository.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepo;

    @Test
    public void should_ReturnUser_When_UserExists() {
        User admin = new User("admin", "pass", UserType.ADMIN);
        entityManager.persist(admin);
        entityManager.flush();
        User found = userRepo.findByUserName("admin");
        assertThat(found).isEqualTo(admin);
    }

    @Test
    public void should_ReturnCustomer_When_UserExists() {
        User user = new User("bob", "pass", UserType.CLIENT);
        Customer customer = new Customer("Bob", "Mark", "bmark@gmail.com");
        user.setCustomer(customer);
        entityManager.persist(user);
        entityManager.flush();
        User found = userRepo.findByUserName("bob");
        assertThat(found).isEqualTo(user);
    }
}
