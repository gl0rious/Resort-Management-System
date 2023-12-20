package edu.miu.cs.cs544.service.impl;

import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.dto.ProductDTO;
import edu.miu.cs.cs544.exception.ResourceNotFoundException;
import edu.miu.cs.cs544.repository.ProductRepository;
import edu.miu.cs.cs544.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ProductDTO getProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(Product.class, id));
        return ProductDTO.from(product);
    }

    @Override
    public ProductDTO updateProduct(int id, ProductDTO productDTO) {
        getProduct(id);
        Product productRequest = productDTO.to();
        productRequest.setId(id);
        Product productResponse = productRepository.save(productRequest);
        return ProductDTO.from(productResponse);
    }

    @Override
    public void deleteProduct(int id) {
        getProduct(id);
        productRepository.deleteById(id);
    }

}
