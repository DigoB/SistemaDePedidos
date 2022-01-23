package br.com.rodrigobraz.OrderSystem.domain;

import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String document;

    private Integer type;

    @OneToMany(mappedBy = "customer")
    private List<CustomerAddress> adresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE_NUMBER")
    private Set<String> phoneNumbers = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<OrderBuy> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer id, String name, String email, String document, CustomerType type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.type = type.getCod();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public CustomerType getType() {
        return CustomerType.toEnum(type);
    }

    public List<CustomerAddress> getAdresses() {
        return adresses;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public List<OrderBuy> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
