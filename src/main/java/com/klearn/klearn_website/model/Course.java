package com.klearn.klearn_website.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Course name cannot be null")
    @Size(min = 1, max = 255, message = "Course name must be between 1 and 255 characters")
    @Column(name = "course_name", nullable = false, length = 255)
    private String course_name;

    @NotNull(message = "Course level cannot be null")
    @Size(min = 1, max = 50, message = "Course level must be between 1 and 50 characters")
    @Column(name = "course_level", nullable = false, length = 50)
    private String course_level;

    @Lob
    @Column(name = "course_description", columnDefinition = "TEXT")
    private String course_description;

    @Size(max = 255, message = "Course image URL must be at most 255 characters")
    @Column(name = "course_image", length = 255)
    private String course_image;

    @NotNull(message = "Course price cannot be null")
    @PositiveOrZero(message = "Course price must be zero or a positive value")
    @Digits(integer = 18, fraction = 0, message = "Course price must be a valid VND amount without decimals")
    @Column(name = "course_price", precision = 18, scale = 0)
    private BigDecimal course_price;

    @Column(name = "created_at", columnDefinition = "DATETIME2")
    private LocalDateTime created_at;

    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @Column(name = "is_deleted")
    private Boolean is_deleted;
}
