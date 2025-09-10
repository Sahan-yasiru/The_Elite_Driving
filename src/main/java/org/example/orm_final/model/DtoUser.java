package org.example.orm_final.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DtoUser {
    private String id;
    private DtoUserType userType;
    private String userName;
    private String passWold;

}
