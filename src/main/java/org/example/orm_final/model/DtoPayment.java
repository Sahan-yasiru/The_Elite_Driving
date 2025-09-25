package org.example.orm_final.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DtoPayment {
    private String paymentId;

    private double amount;

    private LocalDate date;

    private String student;
}
