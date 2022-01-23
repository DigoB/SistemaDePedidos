package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.OrderBuy;
import br.com.rodrigobraz.OrderSystem.services.CategoryService;
import br.com.rodrigobraz.OrderSystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Optional<OrderBuy> order = service.search(id);

        return ResponseEntity.ok().body(order);

    }
}
