package edu.miu.cs.cs544.domain;

import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @Enumerated(value = EnumType.STRING)
    private ReservationStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<Item> items;

    @Embedded
    private AuditData auditData;

}
