package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.dto.ProductDTO;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return ProductDTO.fromList(productRepository.findAll());
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product productRequest = productDTO.to();
        Product productResponse = productRepository.save(productRequest);
        return ProductDTO.from(productResponse);
    }

    @Override
    public Optional<ProductDTO> getProduct(int id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        return ProductDTO.fromOptional(optionalProduct);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        Product productRequest = productDTO.to();
        productRequest.setId(id);
        Product productResponse = productRepository.save(productRequest);
        return ProductDTO.from(productResponse);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

}
