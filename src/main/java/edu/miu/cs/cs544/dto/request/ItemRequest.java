package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.AuditData;
import edu.miu.cs.cs544.domain.Item;
import edu.miu.cs.cs544.domain.Product;
import edu.miu.cs.cs544.domain.Reservation;
import jakarta.persistence.Embedded;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ItemRequest {
    @NotNull
    @Positive
    private Integer occupants;

    private LocalDate checkinDate;

    private LocalDate checkoutDate;

    private Integer productId;

    public Item to() {
        return new Item(null, occupants, checkinDate, checkinDate, null, null, null);
    }

}
