package org.example.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "student")
public class Student {
    @Id
    @Column(name = "stu_ID")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false,name = "tel_NO",length = 10)
    private int phoneNumber;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Payment>  payments;


    @Cascade({org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.MERGE})
    @ManyToMany
    @JoinTable(name = "student_course",
            joinColumns = @JoinColumn(name = "stu_ID"),
            inverseJoinColumns = @JoinColumn(name = "course_ID"))
    private List<Course> courses;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

}
