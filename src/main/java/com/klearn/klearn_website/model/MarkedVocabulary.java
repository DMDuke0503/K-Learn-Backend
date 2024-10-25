package com.klearn.klearn_website.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "marked_vocabulary")
public class MarkedVocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set a default value for is_deleted
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vocabulary_id", referencedColumnName = "id", nullable = false)
    private Vocabulary vocabulary;
}
