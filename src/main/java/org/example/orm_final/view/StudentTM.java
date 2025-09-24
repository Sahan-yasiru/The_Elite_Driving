package org.example.orm_final.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orm_final.view.label.TMLBL;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentTM {
    private String id;

    private String name;

    private String email;

    private int phoneNumber;

    private List<TMLBL> payments;

    private List<TMLBL> courses;

    private List<TMLBL> lessons;
}
