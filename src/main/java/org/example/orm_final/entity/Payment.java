package org.example.orm_final.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_ID")
    private String paymentId;

    private double amount;

    @UpdateTimestamp
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "stu_ID")
    private Student student;
}
