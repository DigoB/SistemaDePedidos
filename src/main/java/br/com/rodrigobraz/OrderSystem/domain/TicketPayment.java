package br.com.rodrigobraz.OrderSystem.domain;

import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("ticketPayment")
public class TicketPayment extends Payment {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
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

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
