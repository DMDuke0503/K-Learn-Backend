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
@Table(name = "vocabulary_topic")
public class VocabularyTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "Topic name cannot be null")
    @Size(max = 50, message = "Topic name must be at most 50 characters")
    @Column(name = "topic_name", nullable = false, length = 50)
    private String topic_name;

    @Column(name = "topic_description", columnDefinition = "TEXT")
    private String topic_description;

    @Column(name = "topic_image", columnDefinition = "TEXT")
    private String topic_image;

    @NotNull(message = "Creation timestamp cannot be null")
    @Column(name = "created_at", columnDefinition = "DATETIME2")
    private LocalDateTime created_at;

    @NotNull(message = "Last modified timestamp cannot be null")
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @NotNull(message = "Course reference cannot be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false)
    private Course course;

    // Lifecycle callbacks to manage timestamps
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
