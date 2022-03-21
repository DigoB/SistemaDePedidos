package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerPostDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import br.com.rodrigobraz.OrderSystem.services.impl.CustomerServiceImpl;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystem.services.validators.DuplicatedEmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private DuplicatedEmailValidator validator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validator);
    }

    @Autowired
    private CustomerServiceImpl service;

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(findById(id), CustomerDTO.class));

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
        return ResponseEntity.ok().body(mapper.map(service.update(dto), CustomerDTO.class));
    }

    @PostMapping
    public ResponseEntity<CustomerPostDTO> insert(@RequestBody @Valid CustomerPostDTO dto) {
        Customer customer = dto.toModel();
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id]").buildAndExpand(service.insert(customer).getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}