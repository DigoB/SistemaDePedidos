package br.com.rodrigobraz.OrderSystem.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Endereco() {
    }

    public Endereco(Integer id, String street, String number, String complement, String district, String zipCode, Customer customer, City city) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.zipCode = zipCode;
        this.customer = customer;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public City getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco)) return false;
        Endereco adress = (Endereco) o;
        return Objects.equals(getId(), adress.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
