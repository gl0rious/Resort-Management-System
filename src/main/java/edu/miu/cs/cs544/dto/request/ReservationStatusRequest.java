package edu.miu.cs.cs544.dto.request;

import edu.miu.cs.cs544.domain.ReservationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationStatusRequest {

    @NotNull
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
