package org.example.orm_final.view.instructor;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.*;
import org.example.orm_final.view.label.TMLBL;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class InstructorTM {
    private String id;
    private String name;
    private String email;
    private List<TMLBL> lessons;
}
