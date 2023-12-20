package edu.miu.cs.cs544.dto;

import edu.miu.cs.cs544.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Integer id;
    private Integer customerID;
    private ReservationStatus status;
    private List<Item> items;
    private AuditData auditData;

    public static ReservationDTO from(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getCustomer().getId(), reservation.getStatus(), reservation.getItems(), reservation.getAuditData());
    }

    public static List<ReservationDTO> fromList(List<Reservation> reservations) {
        return reservations.stream().map(ReservationDTO::from).toList();
    }

    public static Optional<ReservationDTO> fromOptional(Optional<Reservation> optionalReservation) {
        return optionalReservation.map(ReservationDTO::from);
    }

    public Reservation to() {
        return new Reservation(id, new Customer(), status, items, auditData);
    }
}