package com.klearn.klearn_website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "grammar_progress")
public class GrammarProgress {

    @EmbeddedId
    private GrammarProgressId id;

    @NotNull(message = "Theory learning status cannot be null")
    @Column(name = "is_learned_theory", nullable = false)
    private Boolean is_learned_theory = false;

    @NotNull(message = "Quiz finish status cannot be null")
    @Column(name = "is_finish_quiz", nullable = false)
    private Boolean is_finish_quiz = false;

    @NotNull(message = "Quiz failure status cannot be null")
    @Column(name = "is_failed_quiz", nullable = false)
    private Boolean is_failed_quiz = false;

    // Mapping LocalDateTime to DATETIME2 for SQL Server compatibility
    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    @NotNull(message = "Deletion status cannot be null")
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grammar_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Grammar grammar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    // Lifecycle callback to update last_modified timestamp
    @PreUpdate
    public void preUpdate() {
        this.last_modified = LocalDateTime.now();
    }

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GrammarProgressId implements Serializable {
        @NotNull(message = "User ID cannot be null")
        @Column(name = "user_id")
        private Integer user_id;

        @NotNull(message = "Grammar ID cannot be null")
        @Column(name = "grammar_id")
        private Integer grammar_id;

        @NotNull(message = "Course ID cannot be null")
        @Column(name = "course_id")
        private Integer course_id;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            GrammarProgressId that = (GrammarProgressId) o;
            return Objects.equals(user_id, that.user_id) &&
                    Objects.equals(grammar_id, that.grammar_id) &&
                    Objects.equals(course_id, that.course_id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user_id, grammar_id, course_id);
        }
    }
}
