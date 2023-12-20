package edu.miu.cs.cs544.cs544.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.cs.cs544.controller.ProductController;
import edu.miu.cs.cs544.domain.*;
import edu.miu.cs.cs544.dto.ProductDTO;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    MockMvc mock;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @WithMockUser(username = "mahmoud", roles = "USER")
    public void testGetProduct() throws Exception {
        Product product = new Product();
        product.setId(1);
        Mockito.when(productService.getProduct(1)).thenReturn(ProductDTO.from(product));
        mock.perform(MockMvcRequestBuilders.get("/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    @WithMockUser(username = "mahmoud", roles = "USER")
    public void testGetNotExistProduct() throws Exception {
        Product product = new Product();
        product.setId(1);

        Mockito.when(productService.getProduct(1)).thenThrow(new ResourceNotFoundException(Product.class, 1));
        mock.perform(MockMvcRequestBuilders.get("/products/{id}", 1).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "mahmoud", roles = "USER")
    public void shouldCreateNewProductWhenNoProduct() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setNightlyRate(2);
        product.setName("product1");
        product.setMaximumCapacity(3);
        product.setType(ProductType.Room);

        ProductDTO productDTO = ProductDTO.from(product);
        String json = objectMapper.writeValueAsString(productDTO);
        Mockito.when(productService.createProduct(productDTO)).thenReturn(productDTO.from(product));

        mock.perform(MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

    }

    @Test
    @WithMockUser(username = "mahmoud", roles = "USER")
    public void shouldReturnListProductWhen() throws Exception {
        Product product = new Product();
        product.setId(1);
        product.setNightlyRate(2);
        product.setName("product1");
        product.setMaximumCapacity(3);
        product.setType(ProductType.Room);

        Product product2 = new Product();
        product2.setId(2);
        product2.setNightlyRate(2);
        product2.setName("product2");
        product2.setMaximumCapacity(3);
        product2.setType(ProductType.Villa);

        List<ProductDTO> productDTOList = new ArrayList<>();
        productDTOList.add(ProductDTO.from(product));
        productDTOList.add(ProductDTO.from(product2));

        Mockito.when(productService.getAllProducts()).thenReturn(productDTOList);

        mock.perform(MockMvcRequestBuilders.get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("product1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("Room"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("product2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].type").value("Villa"));
    }

}