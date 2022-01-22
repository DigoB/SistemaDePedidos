package br.com.rodrigobraz.OrderSystem.domain;

import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class TicketPayment extends Payment {

    private Date dueDate;

    private Date paymentDate;

    public TicketPayment() {
        super();
    }

    public TicketPayment(Integer id, PaymentStatus status, OrderBuy orderBuy, Date dueDate, Date paymentDate) {
        super(id, status, orderBuy);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }
}
