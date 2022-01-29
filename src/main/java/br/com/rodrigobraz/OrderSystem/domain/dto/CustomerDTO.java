package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerDTO {

    private Integer id;

    @NotBlank(message = "Must not be blank")
    @Length(min = 3, max = 100, message = "Must be between 3 and 100 letters")
    private String name;

    @NotBlank(message = "Must not be blank")
    @Email(message = "Invalid email")
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        name = customer.getName();
        email = customer.getEmail();
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

    public static Page<CustomerDTO> convert(Page<Customer> customers) {
        return customers.map(CustomerDTO::new);
    }

    public Customer toModel() {
        return new Customer(name, email, null, null);
    }

    public Customer update(Integer id, CustomerRepository repository) {
        Customer customer = repository.findById(id).get();
        customer.setName(this.name);
        customer.setEmail(this.email);

        return customer;
    }
}
