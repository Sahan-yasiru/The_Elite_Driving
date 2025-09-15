package org.example.orm_final.entity.user;

import jakarta.persistence.*;
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
    private int id;

    @Column(name = "User_type")
    private UserType userType;

    @Column(name = "user_name",unique = true)
    private String userName;

    @Column(name = "password")
    private String passWold;

}
