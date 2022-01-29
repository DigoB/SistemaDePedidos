package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerPostDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import br.com.rodrigobraz.OrderSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Customer>> searchById(@PathVariable Integer id) {

        Optional<Customer> possibleCustomer = service.search(id);

        return ResponseEntity.ok().body(possibleCustomer);
    }

    @GetMapping
    public Page<CustomerDTO> customers(@RequestParam(required = false) String name,
                                       @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                               Pageable pagination) {
        if (name == null) {
            Page<Customer> customers = service.searchList(pagination);
            return CustomerDTO.convert(customers);
        } else {
            Page<Customer> customers = service.findByName(name, pagination);
            return CustomerDTO.convert(customers);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CustomerDTO> update(@PathVariable Integer id, @RequestBody @Valid CustomerDTO dto) {

        Optional<Customer> possibleCustomer = repository.findById(id);
        if (possibleCustomer.isPresent()) {
            Customer customer = dto.update(id, repository);
            return ResponseEntity.ok(new CustomerDTO(customer));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<CustomerPostDTO> insert(@RequestBody @Valid CustomerPostDTO dto, UriComponentsBuilder uri) {

        Customer customer = dto.toModel();
        customer = service.insert(customer);

        URI path = uri.path("/customers/{id}").buildAndExpand(customer.getId()).toUri();

        return ResponseEntity.created(path).build();
    }
}