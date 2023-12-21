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
    private Integer customerId;
    private ReservationStatus status;
    private List<ItemResponse> items;

    public static ReservationResponse from(Reservation reservation) {
        List<ItemResponse> items = reservation.getItems().stream()
                .map(ItemResponse::from)
                .collect(Collectors.toList());
        return new ReservationResponse(
                reservation.getId(), reservation.getCustomer().getId(),
                reservation.getStatus(), items);
    }

    public static List<ReservationResponse> fromList(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationResponse::from)
                .collect(Collectors.toList());
    }
}
