package edu.miu.cs.cs544.cs544.repository;

import edu.miu.cs.cs544.domain.Customer;
import edu.miu.cs.cs544.domain.Reservation;
import edu.miu.cs.cs544.domain.User;
import edu.miu.cs.cs544.repository.ReservationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    ReservationRepository repository;


    @Autowired
    ReservationRepository reservationRepository;

    @Test
    public void whenFindByReservationHolderThenReturnReservation() {
        // given
        User user = new User();
        entityManager.persist(user);

        Customer customer = new Customer();
        entityManager.persist(customer);

        Reservation reservation = new Reservation();
        reservation.setCustomer(customer);
        entityManager.persist(reservation);
        entityManager.flush();
        // when
        Reservation found = reservationRepository.findById(1).orElseThrow();
        // then
        assertThat(reservation.getId()).isEqualTo(found.getId());
    }

}