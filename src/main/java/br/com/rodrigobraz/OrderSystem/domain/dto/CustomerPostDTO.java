package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.City;
import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.CustomerAddress;
import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import br.com.rodrigobraz.OrderSystem.services.validators.ValidDocument;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerPostDTO {

    @NotBlank(message = "Must not be blank")
    @Length(min = 3, max = 100, message = "Must be between 3 and 100 letters")
    private String name;

    @NotBlank(message = "Must not be blank")
    @Email(message = "Invalid email")
    private String email;

    @ValidDocument
    private String document;

    private Integer type;

    private String street;

    private String number;

    private String complement;

    private String district;

    private String zipCode;

    private String phone1;

    private String phone2;

    private String phone3;

    private Integer cityId;

    public CustomerPostDTO() {
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

    public Integer getType() {
        return type;
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

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public Customer toModel() {
        Customer customer = new Customer(this.name, this.email, this.document, CustomerType.toEnum(this.type));
        City city = new City(this.cityId, null, null);
        CustomerAddress address = new CustomerAddress(this.street, this.number, this.complement, this.district, this.zipCode, customer, city);
        customer.getAdresses().add(address);

        customer.getPhoneNumbers().add(this.phone1);
        if (this.phone2 != null) {
            customer.getPhoneNumbers().add(this.phone2);
        }
        if (this.phone3 != null) {
            customer.getPhoneNumbers().add(this.phone3);
        }
        return customer;
    }
}