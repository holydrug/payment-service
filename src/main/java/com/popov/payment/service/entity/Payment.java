package com.popov.payment.service.entity;

import com.popov.payment.service.entity.etc.Cycle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number;
    private Integer amount;
    private String memo;
    private String name;
    private String email;
    private String bankAccount;
    private boolean recurring;

    private Cycle cycle;
    private String recurringStartDate;
    private Integer recurringInstallments;

    public Payment(Integer amount, String memo, String name, String email, String bankAccount, boolean recurring, Cycle cycle, String recurringStartDate, Integer recurringInstallments) {
        this.amount = amount;
        this.memo = memo;
        this.name = name;
        this.email = email;
        this.bankAccount = bankAccount;
        this.recurring = recurring;
        this.cycle = cycle;
        this.recurringStartDate = recurringStartDate;
        this.recurringInstallments = recurringInstallments;
    }

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
