package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketPaymentServiceImpl {

    public void fillTicketPayment(TicketPayment payment, Date orderInstant) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderInstant);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setPaymentDate(cal.getTime());
    }
}
