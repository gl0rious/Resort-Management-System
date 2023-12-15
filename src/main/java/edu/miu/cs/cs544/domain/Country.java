package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String code;

	private String name;

	private Integer population;

	@Embedded
	private AuditData auditData;

}
