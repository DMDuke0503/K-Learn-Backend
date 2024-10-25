package com.klearn.klearn_website.model;

import jakarta.persistence.*;

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

    @Column(name = "word", nullable = false, length = 50)
    private String word;

    // Using TEXT for larger data, or NVARCHAR(MAX) in SQL Server
    @Column(name = "definition", columnDefinition = "TEXT", nullable = false)
    private String definition;

    @Column(name = "transcription", columnDefinition = "TEXT")
    private String transcription;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set default value for is_deleted to false
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", referencedColumnName = "id", nullable = false)
    private VocabularyTopic vocabularyTopic;
}
