package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer occupants;

	private LocalDate checkinDate;

	private LocalDate checkoutDate;

	@ManyToOne
	private Reservation order;

	@ManyToOne
	private Product product;

	@Embedded
	private AuditData auditData;

}
