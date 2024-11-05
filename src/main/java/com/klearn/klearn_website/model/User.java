package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Username cannot be null")
    @Size(max = 50, message = "Username must be at most 50 characters")
    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must be at most 100 characters")
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Size(max = 255, message = "Password must be at most 255 characters")
    @Column(name = "password", length = 255)
    private String password;

    @Size(max = 100, message = "Fullname must be at most 100 characters")
    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "dob", columnDefinition = "DATE")
    private LocalDate dob;

    @Size(max = 255, message = "Avatar URL must be at most 255 characters")
    @Column(name = "avatar", length = 255)
    private String avatar;

    @Size(max = 10, message = "Gender must be at most 10 characters")
    @Column(name = "gender", length = 10)
    private String gender;

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "last_login", columnDefinition = "DATETIME2")
    private LocalDateTime last_login;

    @NotNull(message = "Last modified timestamp cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set default value for is_deleted
    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    // Set default value for role
    @NotNull(message = "Role cannot be null")
    @Column(name = "role", nullable = false)
    private Integer role = 0; // 0: learner, 1: admin, 2: content-management

    @Size(max = 50, message = "Type must be at most 50 characters")
    @Column(name = "type", length = 50)
    private String type;

    // Lifecycle callbacks to manage timestamps
    @PrePersist
    public void prePersist() {
        this.last_modified = LocalDateTime.now();
        if (this.last_login == null) {
            this.last_login = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.last_modified = LocalDateTime.now();
    }
}
