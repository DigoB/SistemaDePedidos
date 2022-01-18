package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category search(Integer id) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Category not found!"));
        return category;

    }

}
