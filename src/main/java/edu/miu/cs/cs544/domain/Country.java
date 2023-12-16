package edu.miu.cs.cs544.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name;
	private Integer population;
	@Embedded
	private AuditData auditData;

	public Country(String code, String name, Integer population) {
		this.code = code;
		this.name = name;
		this.population = population;
	}

}
