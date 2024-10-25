package com.klearn.klearn_website.model;

import jakarta.persistence.*;

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

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "date_transaction", columnDefinition = "DATETIME2")
    private LocalDateTime date_transaction;

    // Specify precision and scale for BigDecimal mapping in SQL Server
    @Column(name = "transaction_price", precision = 18, scale = 2)
    private BigDecimal transaction_price;

    @Column(name = "transaction_status")
    private String transaction_status;

    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set a default value for is_deleted
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
}
