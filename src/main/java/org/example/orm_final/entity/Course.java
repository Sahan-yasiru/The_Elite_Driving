package org.example.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "course")
public class Course {

    @Id
    @Column(name = "course_ID")
    private String id;

    private String duration;

    private double free;

    private String description;

    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    List<Lesson> lessons;

}
