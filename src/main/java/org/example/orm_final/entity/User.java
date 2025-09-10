package org.example.orm_final.entity;

import jakarta.persistence.Column;
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
@Entity(name ="user" )
public class User {
    @Id
    @Column(name = "user_ID")
    private String id;

    @Column(name = "User_type")
    private UserType userType;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column(name = "password")
    private String passWold;

}
