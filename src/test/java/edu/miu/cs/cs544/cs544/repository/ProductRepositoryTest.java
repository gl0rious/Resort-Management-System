package edu.miu.cs.cs544.cs544.repository;


import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import edu.miu.cs.cs544.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void should_ReturnProduct_When_ProductExists() {
        // Create a Product without setting an ID
        Product product = new Product();
        product.setNightlyRate(2);
        product.setName("product1");
        product.setMaximumCapacity(3);
        product.setType(ProductType.Room);

        // Persist the Product
        entityManager.persist(product);
        entityManager.flush();

        // Retrieve the Product by its generated ID
        Product found = productRepository.findById(product.getId()).orElseThrow();

        // Assert that the found product is equal to the persisted product
        assertThat(found).isEqualTo(product);
    }
}
