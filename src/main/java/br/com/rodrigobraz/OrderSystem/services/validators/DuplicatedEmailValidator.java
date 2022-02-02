package br.com.rodrigobraz.OrderSystem.services.validators;

import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerPostDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicatedEmailValidator implements Validator {

    @Autowired
    private CustomerRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerPostDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CustomerPostDTO customer = (CustomerPostDTO) target;

        Optional<Customer> possibleCustomer = repository.findByEmail(customer.getEmail());

        if (possibleCustomer.isPresent()) {
            errors.rejectValue("email", null, "Email already registered");
        }
    }
}
