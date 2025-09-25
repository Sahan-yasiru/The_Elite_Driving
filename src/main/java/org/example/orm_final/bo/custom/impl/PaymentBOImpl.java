package org.example.orm_final.bo.custom.impl;

import org.example.orm_final.bo.custom.PaymentBO;
import org.example.orm_final.bo.utill.converter.EtyDToConverter;
import org.example.orm_final.dao.DAOFactory;
import org.example.orm_final.dao.custom.PaymentDAO;
import org.example.orm_final.dao.custom.StudentDAO;
import org.example.orm_final.entity.Payment;
import org.example.orm_final.entity.Student;
import org.example.orm_final.model.DtoPayment;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    private PaymentDAO paymentDAO= (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Payment);
    private StudentDAO studentDAO= (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.Student);
    @Override
    public List<DtoPayment> getAll() throws SQLException {
        List<DtoPayment> payments=new ArrayList<>();
        paymentDAO.getAll().forEach(payment->{
            payments.add(EtyDToConverter.getPaymentDTO(payment));
        });
        return  payments;
    }

    @Override
    public boolean save(DtoPayment dtoPayment) throws SQLException {
        Payment payment=new Payment();
        payment.setPaymentId(dtoPayment.getPaymentId());
        payment.setAmount(dtoPayment.getAmount());
        payment.setDate(dtoPayment.getDate());

        Student student=studentDAO.findById(dtoPayment.getStudent());
        payment.setStudent(student);

        return paymentDAO.save(payment);
    }

    @Override
    public boolean update(DtoPayment dtoPayment) throws SQLException, IOException {
        Payment payment=new Payment();
        payment.setPaymentId(dtoPayment.getPaymentId());
        payment.setAmount(dtoPayment.getAmount());
        payment.setDate(dtoPayment.getDate());

        Student student=studentDAO.findById(dtoPayment.getStudent());
        payment.setStudent(student);

        return paymentDAO.update(payment);
    }

    @Override
    public boolean delete(DtoPayment dtoPayment) throws SQLException, IOException {
        Payment payment=new Payment();
        payment.setPaymentId(dtoPayment.getPaymentId());
        payment.setAmount(dtoPayment.getAmount());
        payment.setDate(dtoPayment.getDate());

        Student student=studentDAO.findById(dtoPayment.getStudent());
        payment.setStudent(student);

        return paymentDAO.delete(payment);
    }

    @Override
    public String getLastID() throws SQLException {
        try {
            String id = paymentDAO.getLastID();
            int intId = Integer.parseInt(id.replaceAll("\\D+", "")) + 1;
            return String.format("P" + "%03d", intId);
        } catch (Exception e) {
            e.printStackTrace();
            return "P001";
        }
    }

    @Override
    public boolean ifExit(DtoPayment dtoLesson) throws SQLException {
        return false;
    }
}
