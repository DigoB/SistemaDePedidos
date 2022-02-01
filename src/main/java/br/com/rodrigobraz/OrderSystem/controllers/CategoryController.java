package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.CategoryService;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> find(@PathVariable Integer id) {

        Optional<Category> category = service.search(id);
        if (category.isEmpty()) {
            throw new ObjectNotFoundException("Category id does not exist");
        }

        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public Page<CategoryDTO> list(@RequestParam(required = false) String name,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                               Pageable pagination) {

        if (name == null) {
            Page<Category> categories = service.searchList(pagination);
            return CategoryDTO.convert(categories);
        } else {
            Page<Category> categories = service.findByName(name, pagination);
            return CategoryDTO.convert(categories);
        }
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody @Valid CategoryDTO dto, UriComponentsBuilder uri) {

        Category category = dto.toModel();
        category = service.insert(category);

        URI path = uri.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(path).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody @Valid CategoryDTO dto) {

        Optional<Category> possibleCategory = repository.findById(id);
        if (possibleCategory.isPresent()) {
            Category category = dto.update(id, repository);
            return ResponseEntity.ok(new CategoryDTO(category));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
