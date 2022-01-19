package br.com.rodrigobraz.OrderSystem.repositories;

import br.com.rodrigobraz.OrderSystem.domain.Endereco;
import br.com.rodrigobraz.OrderSystem.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Endereco, Integer> {
}
