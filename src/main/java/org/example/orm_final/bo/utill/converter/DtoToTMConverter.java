package org.example.orm_final.bo.utill.converter;

import org.example.orm_final.model.user.DtoUser;
import org.example.orm_final.model.user.DtoUserType;
import org.example.orm_final.view.user.TMUserType;
import org.example.orm_final.view.user.UserTM;

public class DtoToTMConverter {
    public static UserTM getUserTM(DtoUser dtoUser){
        UserTM userTM=new UserTM();
        userTM.setId(dtoUser.getId());
        userTM.setUserName(dtoUser.getUserName());
        userTM.setPassWold(dtoUser.getPassWold());
        userTM.setUserType(dtoUser.getUserType().equals(DtoUserType.Admin)? TMUserType.Admin:TMUserType.Receptionist);

        return userTM;
    }
    public static DtoUser getUserDto(UserTM userTM){
        DtoUser dtoUser =new DtoUser();
        dtoUser.setId(userTM.getId());
        dtoUser.setUserName(userTM.getUserName());
        dtoUser.setPassWold(userTM.getPassWold());
        dtoUser.setUserType(userTM.getUserType().equals(TMUserType.Admin)? DtoUserType.Admin:DtoUserType.Receptionist);

        return dtoUser;
    }
}
