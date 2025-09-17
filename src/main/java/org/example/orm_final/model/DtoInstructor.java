package org.example.orm_final.model;

import lombok.*;
import org.example.orm_final.entity.Course;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class DtoInstructor {
    private String id;
    private String name;
    private String nic;
    private List<DtoCourse> courses;
}
