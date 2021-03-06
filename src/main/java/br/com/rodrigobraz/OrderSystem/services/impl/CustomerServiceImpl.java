package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.City;
import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.CustomerAddress;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerPostDTO;
import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import br.com.rodrigobraz.OrderSystem.repositories.AddressRepository;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerServiceImpl {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper mapper;

    public Customer findById(Integer id) {

        Optional<Customer> customer = repository.findById(id);
        return customer.orElseThrow(() ->
                new ObjectNotFoundException("Object not found! Id: " + id + " type: " + Customer.class.getName()));
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Not possible delete a customer that has orders");
        }
    }

    public Customer insert(Customer customer) {
        customer = repository.save(customer);
        addressRepository.saveAll(customer.getAdresses());
        return customer;
    }

    public Customer update(CustomerDTO dto) {
        findById(dto.getId());
        return repository.save(mapper.map(dto, Customer.class));
    }

    public Page<Customer> searchList(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Page<Customer> findByName(String name, Pageable pagination) {
        return repository.findByName(name, pagination);
    }

    public Customer toModel(CustomerPostDTO dto) {
        Customer customer = new Customer(dto.getName(), dto.getEmail(), dto.getDocument(), CustomerType.toEnum(dto.getType()));
        City city = new City(dto.getCityId(),null,null);
        CustomerAddress address = new CustomerAddress(dto.getStreet(),dto.getNumber(),dto.getComplement(),dto.getDistrict(),dto.getZipCode(),customer, city);
        customer.getAdresses().add(address);
        customer.getPhoneNumbers().add(dto.getPhone1());
        if (dto.getPhone2() != null) {
            customer.getPhoneNumbers().add(dto.getPhone2());
        }
        if (dto.getPhone3() != null) {
            customer.getPhoneNumbers().add(dto.getPhone3());
        }
        return customer;
    }
}
