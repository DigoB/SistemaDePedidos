package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Category update(Category category) {
        return repository.save(category);
    }

    public void delete(Integer id) {
        search(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Not possible delete a category that has products");
        }
    }

    public Page<Category> searchList(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Page<Category> findByName(String name, Pageable pagination) {
        return repository.findByName(name, pagination);
    }
}
