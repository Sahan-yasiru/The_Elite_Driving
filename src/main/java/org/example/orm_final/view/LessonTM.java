package org.example.orm_final.view;


import javafx.scene.layout.VBox;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orm_final.view.instructor.InstuctorLBL;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LessonTM {
    private String lessonId;

    private String lessonName;

    private String time;

    private LocalDate date;

    private InstuctorLBL instructorID;

    private InstuctorLBL courseID;

    private InstuctorLBL studentID;

}
