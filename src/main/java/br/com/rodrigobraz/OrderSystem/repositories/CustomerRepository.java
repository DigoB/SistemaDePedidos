package br.com.rodrigobraz.OrderSystem.repositories;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
