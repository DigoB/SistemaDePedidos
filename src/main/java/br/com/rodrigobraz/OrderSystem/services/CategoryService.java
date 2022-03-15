package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category findById(Integer id);
    Category insert(CategoryDTO dto);
    void delete(Integer id);
    Category update(CategoryDTO dto);
    Page<Category> findByName(String name, Pageable pagination);
    Page<Category> findList(Pageable pagination);
}
