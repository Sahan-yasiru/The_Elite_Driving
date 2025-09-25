package org.example.orm_final.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.orm_final.model.DtoStudent;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTM {
    private String paymentId;

    private double amount;

    private LocalDate date;

    private String student_ID;

}
