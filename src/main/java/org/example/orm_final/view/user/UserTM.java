package org.example.orm_final.view.user;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserTM {
    private int id;
    private TMUserType userType;
    private String userName;
    private String passWold;


}

