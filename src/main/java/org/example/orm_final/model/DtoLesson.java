package org.example.orm_final.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DtoLesson {
    private String lessonId;

    private String lessonName;

    private String time;

    private LocalDate date;

    private DtoInstructor instructor;

    private DtoCourse course;

    private DtoStudent student;




}
