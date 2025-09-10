package org.example.orm_final.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "Instructors")
public class Instructor {
    @Id
    @Column(name = "Instructor_ID")
    private String id;

    @Embedded
    private FullName fullName;



}
