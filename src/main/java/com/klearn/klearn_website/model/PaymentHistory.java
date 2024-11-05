package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Transaction date cannot be null")
    @Column(name = "date_transaction", columnDefinition = "DATETIME2")
    private LocalDateTime date_transaction;

    @NotNull(message = "Transaction price cannot be null")
    @PositiveOrZero(message = "Transaction price must be zero or positive")
    @Column(name = "transaction_price", precision = 18, scale = 0) 
    private BigDecimal transaction_price;

    @NotNull(message = "Transaction status cannot be null")
    @Column(name = "transaction_status", length = 50)
    private String transaction_status;

    @NotNull(message = "Last modified timestamp cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @NotNull(message = "User cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @NotNull(message = "Course cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    // Lifecycle callbacks to manage date_transaction and last_modified timestamps
    @PrePersist
    public void prePersist() {
        this.date_transaction = LocalDateTime.now();
        this.last_modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.last_modified = LocalDateTime.now();
    }
}
