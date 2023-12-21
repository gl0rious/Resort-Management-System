package edu.miu.cs.cs544.cs544.repository;

import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void should_ReturnProduct_When_ProductExists() {
        Item item = new Item();
        entityManager.persist(item);
        entityManager.flush();
        // Retrieve the Product by its generated ID
        Item found = itemRepository.findById(item.getId()).orElseThrow();
        // Assert that the found product is equal to the persisted product
        assertThat(found).isEqualTo(item);
    }
}



