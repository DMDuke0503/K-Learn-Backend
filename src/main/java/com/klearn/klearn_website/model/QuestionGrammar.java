package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question_grammar")
public class QuestionGrammar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Question text cannot be null")
    @Size(max = 1000, message = "Question text must be at most 1000 characters")
    @Column(name = "question_text", length = 1000)
    private String question_text;

    @NotNull(message = "Correct answer cannot be null")
    @Size(max = 255, message = "Correct answer must be at most 255 characters")
    @Column(name = "correct_answer", length = 255)
    private String correct_answer;

    @NotNull(message = "Incorrect answer cannot be null")
    @Size(max = 1000, message = "Incorrect answer must be at most 1000 characters")
    @Column(name = "incorrect_answer", length = 1000)
    private String incorrect_answer;

    @NotNull(message = "Quiz type cannot be null")
    @Size(max = 50, message = "Quiz type must be at most 50 characters")
    @Column(name = "quiz_type", length = 50)
    private String quiz_type;

    @NotNull(message = "Creation timestamp cannot be null")
    @Column(name = "created_at", columnDefinition = "DATETIME2")
    private LocalDateTime created_at;

    @NotNull(message = "Last modified timestamp cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @NotNull(message = "Grammar reference cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grammar_id", referencedColumnName = "id", nullable = false)
    private Grammar grammar;

    // Lifecycle callbacks to manage created_at and last_modified timestamps
    @PrePersist
    public void prePersist() {
        this.created_at = LocalDateTime.now();
        this.last_modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.last_modified = LocalDateTime.now();
    }
}
