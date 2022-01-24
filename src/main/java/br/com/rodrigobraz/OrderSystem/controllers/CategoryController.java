package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> find(@PathVariable Integer id) {

        Optional<Category> category = service.search(id);

        return ResponseEntity.ok().body(category);
    }

    @GetMapping
    public Page<CategoryDTO> list(@RequestParam(required = false) String name,
                               @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10)
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
    public ResponseEntity<Category> insert(@RequestBody Category category, UriComponentsBuilder uri) {

        category = service.insert(category);

        URI path = uri.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(path).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Category category, @PathVariable Integer id) {

        service.update(category);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}
