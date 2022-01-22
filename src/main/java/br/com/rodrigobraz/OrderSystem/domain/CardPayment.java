package br.com.rodrigobraz.OrderSystem.domain;

import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class CardPayment extends Payment {

    private Integer installmentNumber;

    public CardPayment() {
        super();
    }

    public CardPayment(Integer id, PaymentStatus status, OrderBuy orderBuy, Integer installmentNumber) {
        super(id, status, orderBuy);
        this.installmentNumber = installmentNumber;
    }

    public Integer getInstallmentNumber() {
        return installmentNumber;
    }
}
