package com.klearn.klearn_website.model;

import jakarta.persistence.*;

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

    @Column(name = "question_text", length = 1000)
    private String question_text;

    @Column(name = "correct_answer", length = 255)
    private String correct_answer;

    @Column(name = "incorrect_answer", length = 1000)
    private String incorrect_answer;

    @Column(name = "quiz_type", length = 50)
    private String quiz_type;

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "created_at", columnDefinition = "DATETIME2")
    private LocalDateTime created_at;

    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set a default value for is_deleted
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grammar_id", referencedColumnName = "id", nullable = false)
    private Grammar grammar;
}
