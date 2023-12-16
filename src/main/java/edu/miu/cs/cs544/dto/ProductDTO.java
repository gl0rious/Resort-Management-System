package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;

    private String name;

    private String description;

    private String excerpt;

    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    private double nightlyRate;

    private int maximumCapacity;

    private AuditData auditData;

    public static ProductDTO from(Product product) {
        return new ProductDTO(
                product.getId(), product.getName(),
                product.getDescription(), product.getExcerpt(),
                product.getType(), product.getNightlyRate(),
                product.getMaximumCapacity(), product.getAuditData()
        );
    }

    public static List<ProductDTO> fromList(List<Product> products) {
        return products.stream().map(ProductDTO::from).toList();
    }

    public static Optional<ProductDTO> fromOptional(Optional<Product> optionalProduct) {
        return optionalProduct.map(ProductDTO::from);
    }

    public Product to() {
        return new Product(id, name, description, excerpt, type, nightlyRate, maximumCapacity, auditData);
    }
}
