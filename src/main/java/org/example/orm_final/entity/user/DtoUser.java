package org.example.orm_final.entity.user;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DtoUser {
    private int id;
    private DtoUserType userType;
    private String userName;
    private String passWold;

}
