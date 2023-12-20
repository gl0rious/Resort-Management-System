package edu.miu.cs.cs544.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.ProductType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;

    @NotBlank
    private String name;

    private String description;

    private String excerpt;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private ProductType type;

    @NotNull
    @Positive
    private Double nightlyRate;

    @NotNull
    @Min(1)
    @Max(50)
    private Integer maximumCapacity;

    @JsonIgnore
    private AuditData auditData;

    @JsonProperty("auditData")
    public AuditData getAuditData() {
        return auditData;
    }

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
