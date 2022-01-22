package br.com.rodrigobraz.OrderSystem.domain;

import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment {

    @Id
    private Integer id;

    private Integer status;

    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private OrderBuy orderBuy;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus status, OrderBuy orderBuy) {
        this.id = id;
        this.status = status.getCod();
        this.orderBuy = orderBuy;
    }

    public Integer getId() {
        return id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public OrderBuy getOrder() {
        return orderBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
