package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.OrderBuy;
import br.com.rodrigobraz.OrderSystem.repositories.OrderRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Optional<OrderBuy> search(Integer id) {

        Optional<OrderBuy> order = repository.findById(id);
        if (order.isEmpty()) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " type: " + Category.class.getName());
        }
        return order;
    }

}
