package br.com.rodrigobraz.OrderSystem.repositories;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
