package com.popov.payment.service.sender.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Payment implements Serializable {

    private Long number;
    private Integer amount;
    private String memo;
    private String name;
    private String email;
    private String bankAccount;
    private boolean recurring;

    private Cycle cycle;

    private Date recurringStartDate;

    private Integer recurringInstallments;



    @Override
    public String toString() {
        return "Payment{" +
                "number=" + number +
                ", amount=" + amount +
                ", memo='" + memo + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", recurring=" + recurring +
                ", cycle=" + cycle +
                ", recurringStartDate='" + recurringStartDate + '\'' +
                ", recurringInstallments=" + recurringInstallments +
                '}';
    }
}
