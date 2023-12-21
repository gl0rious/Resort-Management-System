package edu.miu.cs.cs544.domain;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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

	public Item(long id, Integer occupants, LocalDate chickinDate, LocalDate checkoutDate, Reservation order,
			Product product, AuditData auditData) {
		this.id = (int) id;
		this.occupants = occupants;
		this.checkinDate = chickinDate;
		this.checkoutDate = checkoutDate;
		this.order = order;
		this.product = product;
		this.auditData = auditData;
	}

}
