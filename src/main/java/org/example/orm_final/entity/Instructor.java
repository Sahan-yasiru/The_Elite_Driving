package org.example.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "instructor")
public class Instructor {
    @Id
    @Column(name = "instructor_ID")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String nic;

    @Cascade({CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToMany
    @JoinTable(
            name = "instructor_course",
            joinColumns =  @JoinColumn(name = "instructor_ID"),
            inverseJoinColumns = @JoinColumn(name = "course_ID")
    )
    private List<Course> courses;


}
