package org.example.orm_final.bo.custom;

import org.example.orm_final.bo.SuperBO;
import org.example.orm_final.model.DtoLesson;
import org.example.orm_final.model.DtoPayment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public List<DtoPayment> getAll() throws SQLException;
    public boolean save(DtoPayment dtoPayment)throws SQLException;
    public boolean update(DtoPayment dtoPayment)throws SQLException, IOException;
    public boolean delete(DtoPayment dtoPayment)throws SQLException, IOException;
    public String getLastID()throws SQLException;
    public boolean ifExit(DtoPayment dtoLesson) throws SQLException;
}
