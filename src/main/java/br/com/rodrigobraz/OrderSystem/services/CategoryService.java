package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Optional<Category> search(Integer id) {

        Optional<Category> category = repository.findById(id);
        if (category.isEmpty()) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " type: " + Category.class.getName());
        }
        return category;
    }

    public Category insert(Category category) {
        return repository.save(category);
    }
}
