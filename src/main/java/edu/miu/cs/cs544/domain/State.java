package edu.miu.cs.cs544.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class State {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String code;
	private String name;
	@ManyToOne
	private Country country;
	@Embedded
	private AuditData auditData;

	public State(String code, String name, Country country) {
		this.code = code;
		this.name = name;
		this.country = country;
	}
}
