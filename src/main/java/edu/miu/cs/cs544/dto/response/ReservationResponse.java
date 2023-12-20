package edu.miu.cs.cs544.dto.response;

import edu.miu.cs.cs544.domain.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {

    private Integer id;

    private Customer customer;

    private ReservationStatus status;

    private List<Item> items;

    private AuditData auditData;

    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(), reservation.getCustomer(),
                reservation.getStatus(), reservation.getItems(),
                reservation.getAuditData()
        );
    }

    public static List<ReservationResponse> fromList(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }

    public Reservation to() {
        return new Reservation(id, customer, status, items, auditData);
    }

}
