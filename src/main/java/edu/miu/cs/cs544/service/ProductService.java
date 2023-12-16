package edu.miu.cs.cs544.service;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> getAllProducts();
    ProductDTO createProduct(ProductDTO productDTO);
    Optional<ProductDTO> getProduct(int id);
    ProductDTO updateProduct(int id, ProductDTO product);
    void deleteProduct(int id);

}
