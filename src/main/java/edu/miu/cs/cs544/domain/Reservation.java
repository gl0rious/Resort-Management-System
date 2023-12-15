package edu.miu.cs.cs544.domain;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Customer customer;

	@Enumerated(value = EnumType.STRING)
	private ReservationStatus status;

	@OneToMany(mappedBy = "order")
	private List<Item> items;

	@Embedded
	private AuditData auditData;

}
