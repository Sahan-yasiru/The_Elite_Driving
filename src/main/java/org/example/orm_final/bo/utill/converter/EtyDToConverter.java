package org.example.orm_final.bo.utill.converter;

import org.example.orm_final.entity.user.User;
import org.example.orm_final.entity.user.UserType;
import org.example.orm_final.model.user.DtoUser;
import org.example.orm_final.model.user.DtoUserType;

public class EtyDToConverter {

    public static DtoUser getUserDto(User ety){
        DtoUser dtoUser=new DtoUser();
        dtoUser.setId(ety.getId());
        dtoUser.setUserName(ety.getUserName());
        dtoUser.setPassWold(ety.getPassWold());
        dtoUser.setUserType(ety.getUserType().equals(UserType.Admin)? DtoUserType.Admin:DtoUserType.Receptionist);

        return dtoUser;
    }
    public static User getUserEty(DtoUser dtoUser){
        User user =new User();
        user.setId(dtoUser.getId());
        user.setUserName(dtoUser.getUserName());
        user.setPassWold(dtoUser.getPassWold());
        user.setUserType(dtoUser.getUserType().equals(DtoUserType.Admin)? UserType.Admin:UserType.Receptionist);

        return user;
    }

}
