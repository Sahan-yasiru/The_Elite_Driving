package org.example.orm_final.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DtoStudent {

    private String id;

    private String name;

    private String email;

    private int phoneNumber;

    private List<String>  payments;

    private List<String> courses;

    private List<String> lessons;


}
