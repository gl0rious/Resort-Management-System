package edu.miu.cs.cs544.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Embeddable
public class AuditData {
    private String createdBy;

    private String updatedBy;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;
}
