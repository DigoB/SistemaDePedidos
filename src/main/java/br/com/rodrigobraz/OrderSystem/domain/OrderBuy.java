package br.com.rodrigobraz.OrderSystem.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class OrderBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private CustomerAddress deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public OrderBuy() {
    }

    public OrderBuy(Integer id, Date instant, Customer customer, CustomerAddress deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue() {
        double sum = 0;
        for (OrderItem item : items) {
            sum += item.getSubTotal();
        }
        return sum;
    }

    public Integer getId() {
        return id;
    }

    public Date getInstant() {
        return instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderBuy)) return false;
        OrderBuy orderBuy = (OrderBuy) o;
        return Objects.equals(getId(), orderBuy.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
