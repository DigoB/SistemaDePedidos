package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.OrderBuy;
import br.com.rodrigobraz.OrderSystem.services.impl.OrderServiceImpl;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<Optional<OrderBuy>> find(@PathVariable Integer id) {

        Optional<OrderBuy> order = service.search(id);
        if (order.isEmpty()) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + OrderBuy.class.getName());
        }
        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<OrderBuy> insert(@RequestBody OrderBuy order, UriComponentsBuilder uri) {

        service.insert(order);

        URI path = uri.path("/orders/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(path).body(order);

    }

}
