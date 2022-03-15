package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.CategoryService;
import br.com.rodrigobraz.OrderSystem.services.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository repository;


    @Autowired
    private ModelMapper mapper;

    public Category findById(Integer id) {

        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() ->
                new ObjectNotFoundException("Object not found! Id: " + id + " type: " + Category.class.getName()));
    }

    @Override
    public Category insert(CategoryDTO dto) {
        findById(dto.getId());
        return repository.save(mapper.map(dto, Category.class));
    }

    public void delete(Integer id) {
        findById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Not possible delete a category that has products");
        }
    }

    @Override
    public Category update(CategoryDTO dto) {
        findById(dto.getId());
        return repository.save(mapper.map(dto, Category.class));
    }

    public Page<Category> findList(Pageable pagination) {
        return repository.findAll(pagination);
    }

    public Page<Category> findByName(String name, Pageable pagination) {
        return repository.findByName(name, pagination);
    }
}
