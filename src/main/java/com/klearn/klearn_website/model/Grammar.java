package com.klearn.klearn_website.model;

import jakarta.persistence.*;
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

    @Column(name = "grammar_name", nullable = false, length = 255)
    private String grammar_name;

    // Use NVARCHAR(MAX) for large text fields in SQL Server
    @Column(name = "grammar_description", columnDefinition = "NVARCHAR(MAX)")
    private String grammar_description;

    @Column(name = "explanation", columnDefinition = "NVARCHAR(MAX)")
    private String explanation;

    @Column(name = "example", columnDefinition = "NVARCHAR(MAX)")
    private String example;

    @Column(name = "lesson_number")
    private Integer lesson_number;

    // Specify DATETIME2 for LocalDateTime mapping
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    // Many-to-One relationship with Course
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;
}
