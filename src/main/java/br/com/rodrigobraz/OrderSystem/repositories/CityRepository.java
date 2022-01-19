package br.com.rodrigobraz.OrderSystem.repositories;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
}
