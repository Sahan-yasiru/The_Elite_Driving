package org.example.orm_final.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orm_final.entity.Lesson;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DtoInstructor {

    private String id;

    private String name;

    private String email;

    private List<String> lessons;


}
