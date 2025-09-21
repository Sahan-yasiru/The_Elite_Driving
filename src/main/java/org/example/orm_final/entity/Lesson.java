package org.example.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Lesson {
    @Id
    @Column(name = "lesson_id")
    private String lessonId;

    @Column(name = "name")
    private String lessonName;

    private String time;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "instructor_ID")
    private Instructor instructor;

    @ManyToOne
    @JoinColumn(name = "course_ID")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "stu_ID")
    private Student student;

}
