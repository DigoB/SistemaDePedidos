package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Optional<Customer> search(Integer id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer == null) {
            throw new ObjectNotFoundException("Object not found! Id: " +
                    id + " Type: " + Customer.class.getName());
        }

        return customer;
    }


}
