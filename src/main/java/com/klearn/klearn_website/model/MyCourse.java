package com.klearn.klearn_website.model;

import jakarta.persistence.*;
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
@Table(name = "my_course")
public class MyCourse {

    @EmbeddedId
    private MyCourseId id;

    // Use DATETIME2 for SQL Server compatibility
    @Column(name = "date_registration", columnDefinition = "DATETIME2")
    private LocalDateTime date_registration;

    @Column(name = "payment_status")
    private String payment_status;

    @Column(name = "last_modified", columnDefinition = "DATETIME2")
    private LocalDateTime last_modified;

    // Set a default value for is_deleted
    @Column(name = "is_deleted", nullable = false)
    private Boolean is_deleted = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Course course;

    @Embeddable
    public static class MyCourseId implements Serializable {

        @Column(name = "user_id")
        private Integer user_id;

        @Column(name = "course_id")
        private Integer course_id;

        public MyCourseId() {
        }

        public MyCourseId(Integer user_id, Integer course_id) {
            this.user_id = user_id;
            this.course_id = course_id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            MyCourseId that = (MyCourseId) o;
            return Objects.equals(user_id, that.user_id) &&
                    Objects.equals(course_id, that.course_id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(user_id, course_id);
        }
    }
}
