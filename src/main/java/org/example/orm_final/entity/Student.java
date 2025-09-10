package org.example.orm_final.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "Student")
public class Student {
    @Id
    @Column(name = "stu_ID")
    String id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    int age;
    @Column(nullable = false,unique = true,length = 12)
    String nic;
}
