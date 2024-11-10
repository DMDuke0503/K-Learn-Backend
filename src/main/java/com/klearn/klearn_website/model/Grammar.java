package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grammar")
public class Grammar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Grammar name cannot be null")
    @Size(min = 1, max = 255, message = "Grammar name must be between 1 and 255 characters")
    @Column(name = "grammar_name", nullable = false, length = 255)
    private String grammar_name;

    @Lob
    @Column(name = "grammar_description", columnDefinition = "NVARCHAR(MAX)")
    private String grammar_description;

    @Lob
    @Column(name = "explanation", columnDefinition = "NVARCHAR(MAX)")
    private String explanation;

    @Lob
    @Column(name = "example", columnDefinition = "NVARCHAR(MAX)")
    private String example;

    @NotNull(message = "Lesson number cannot be null")
    @Min(value = 1, message = "Lesson number must be greater than or equal to 1")
    @Column(name = "lesson_number")
    private Integer lesson_number;

    // Specify DATETIME2 for LocalDateTime mapping
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    // Many-to-One relationship with Course
    @NotNull(message = "Course cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
}
