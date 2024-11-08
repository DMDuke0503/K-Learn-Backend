package com.klearn.klearn_website.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comprehensive_vocabulary_test_answer")
public class ComprehensiveVocabularyTestAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "user_answer", columnDefinition = "NVARCHAR(MAX)")
    private String user_answer;

    @Column(name = "word", columnDefinition = "NVARCHAR(255)")
    private String word;

    @Column(name = "type", columnDefinition = "NVARCHAR(255)")
    private String type;

    @Column(name = "definition", columnDefinition = "NVARCHAR(MAX)")
    private String definition;

    @Column(name = "options", columnDefinition = "NVARCHAR(MAX)")
    private String options;

    @NotNull(message = "Last modified date cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @NotNull(message = "is_deleted field cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @NotNull(message = "is_correct field cannot be null")
    @Column(name = "is_correct", nullable = false)
    private Boolean is_correct = false;

    @NotNull(message = "Comprehensive test results cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprehensive_test_results_id", referencedColumnName = "id", nullable = false)
    private ComprehensiveTestResults comprehensiveTestResults;
}
