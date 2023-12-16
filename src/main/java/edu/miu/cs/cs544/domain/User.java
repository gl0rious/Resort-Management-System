package edu.miu.cs.cs544.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true)
	private String userName;
	private String userPass;
	private Boolean active;
	@OneToOne
	private Customer customer;
	@Enumerated(value = EnumType.STRING)
	private UserType type;
	@Embedded
	private AuditData auditData;

	public User(String userName, String userPass, UserType type) {
		this.userName = userName;
		this.userPass = userPass;
		this.type = type;
		this.active = true;
	}
}
