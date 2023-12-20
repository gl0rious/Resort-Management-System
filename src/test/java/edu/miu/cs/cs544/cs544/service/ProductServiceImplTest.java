package edu.miu.cs.cs544.cs544.service;

import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.ProductDTO;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        Product product = new Product();
        product.setId(1);
        product.setNightlyRate(2);
        product.setName("product1");
        product.setMaximumCapacity(3);
        product.setType(ProductType.Room);

        Optional<Product> productOptional = Optional.of(product);
        Mockito.when(productRepository.findById(1)).thenReturn(productOptional);

        product.setType(ProductType.Villa);
        Optional<Product> productOptional1 = Optional.of(product);
        Mockito.when(productRepository.findById(1)).thenReturn(productOptional1);
    }

    @Test
    @WithMockUser(username = "mahmoud", roles = "USER")
    public void whenValidProductThenProductShouldBeFound() {
        ProductDTO found = productService.getProduct(1);
        assertThat(found.getId()).isEqualTo(1);
    }

    @Test
    public void whenUpdateProductThenProductShouldBeFound() {
        ProductDTO found = productService.getProduct(1);
        assertThat(found.getId()).isEqualTo(1);
        assertThat(found.getType()).isEqualTo(ProductType.Villa);
    }

}