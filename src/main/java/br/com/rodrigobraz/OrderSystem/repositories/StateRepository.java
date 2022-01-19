package br.com.rodrigobraz.OrderSystem.repositories;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
}
