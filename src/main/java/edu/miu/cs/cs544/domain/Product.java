package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String description;

	private String excerpt;

	@Enumerated(value = EnumType.STRING)
	private ProductType type;

	private double nightlyRate;

	private int maximumCapacity;

	@Embedded
	private AuditData auditData;

}
