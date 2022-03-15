package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.OrderBuy;
import br.com.rodrigobraz.OrderSystem.domain.OrderItem;
import br.com.rodrigobraz.OrderSystem.domain.TicketPayment;
import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;
import br.com.rodrigobraz.OrderSystem.repositories.OrderItemRepository;
import br.com.rodrigobraz.OrderSystem.repositories.OrderRepository;
import br.com.rodrigobraz.OrderSystem.repositories.PaymentRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository itemRepository;

    @Autowired
    TicketPaymentServiceImpl ticketService;

    @Autowired
    private ProductServiceImpl productService;

    public Optional<OrderBuy> search(Integer id) {

        Optional<OrderBuy> order = repository.findById(id);
        if (order.isEmpty()) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " type: " + Category.class.getName());
        }
        return order;
    }

    @Transactional
    public OrderBuy insert(OrderBuy order) {
        order.setInstant(new Date());
        order.getPayment().setStatus(PaymentStatus.PENDING);
        order.getPayment().setOrder(order);
        if (order.getPayment() instanceof TicketPayment) {
            TicketPayment payment = (TicketPayment) order.getPayment();
            ticketService.fillTicketPayment(payment,order.getInstant());
        }
        repository.save(order);
        paymentRepository.save(order.getPayment());
        for (OrderItem item : order.getItems()) {
            item.setDiscount(0.0);
            item.setPrice(productService.findById(item.getProduct().getId()).get().getPrice());
            item.setOrder(order);
        }
        itemRepository.saveAll(order.getItems());
        return order;
    }
}
