package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String line1;

	private String line2;

	private String city;

	private String postalCode;

	@ManyToOne
	private State state;

	@Embedded
	private AuditData auditData;

}
