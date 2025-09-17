package org.example.orm_final.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orm_final.entity.Instructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CourseTM {
    private String id;
    private String duration;
    private double free;
    private String description;
    private List<Instructor> instructors;
}
