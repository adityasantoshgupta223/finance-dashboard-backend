package com.example.Finance.Models;

import com.example.Finance.Models.enums.RecordType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "financeRecord")
public class FinancialRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long recordId;

    @Column(nullable = false)
    @Min(value = 1, message = "Amount must be at least 1")
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users createdBy;

    private String category;
    private String note;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Record type cannot be null")
    private RecordType recordType;

    @Column(nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDate.now();
    }

    public boolean get() {
        return false;
    }
}
