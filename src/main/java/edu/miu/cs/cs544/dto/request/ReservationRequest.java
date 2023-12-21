package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private Integer id;
    private Integer customerId;
    private ReservationStatus status;
    private List<ItemRequest> items;

    // public static ReservationRequest from(Reservation reservation) {
    // return new ReservationRequest(reservation.getId(),
    // reservation.getCustomer().getId(), reservation.getStatus(),
    // reservation.getItems(), reservation.getAuditData());
    // }

    // public static List<ReservationRequest> fromList(List<Reservation>
    // reservations) {
    // return reservations.stream().map(ReservationRequest::from).toList();
    // }

    // public static Optional<ReservationRequest> fromOptional(Optional<Reservation>
    // optionalReservation) {
    // return optionalReservation.map(ReservationRequest::from);
    // }

    public static Reservation to(ReservationRequest request) {
        List<Item> items = request.getItems().stream().map(ItemRequest::to).toList();
        Reservation reservation = new Reservation();
        reservation.setId(request.getId());
        reservation.setStatus(request.getStatus());
        reservation.setItems(items);
        return reservation;
    }
}