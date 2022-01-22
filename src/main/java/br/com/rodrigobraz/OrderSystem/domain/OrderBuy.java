package br.com.rodrigobraz.OrderSystem.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class OrderBuy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderBuy")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "delivery_address_id")
    private Endereco deliveryAddress;

    public OrderBuy() {
    }

    public OrderBuy(Integer id, Date instant, Customer customer, Endereco deliveryAddress) {
        this.id = id;
        this.instant = instant;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
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

    public Endereco getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
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
