package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vocabulary")
public class Vocabulary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Word cannot be null")
    @Size(max = 50, message = "Word must be at most 50 characters")
    @Column(name = "word", nullable = false, length = 50)
    private String word;

    @NotNull(message = "Definition cannot be null")
    @Column(name = "definition", columnDefinition = "TEXT", nullable = false)
    private String definition;

    @Column(name = "transcription", columnDefinition = "TEXT")
    private String transcription;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    // Use DATETIME2 for SQL Server compatibility
    @NotNull(message = "Last modified timestamp cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set default value for is_deleted to false
    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @NotNull(message = "Vocabulary topic cannot be null")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
    private VocabularyTopic vocabularyTopic;

    // Lifecycle callbacks to manage last_modified timestamp
    @PrePersist
    public void prePersist() {
        this.last_modified = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.last_modified = LocalDateTime.now();
    }
}
