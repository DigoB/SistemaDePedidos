package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.impl.CategoryServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl service;

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(mapper.map(service.findById(id), CategoryDTO.class));
    }

    @GetMapping
    public Page<CategoryDTO> list(@RequestParam(required = false) String name,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                               Pageable pagination) {

        if (name == null) {
            Page<Category> categories = service.findList(pagination);
            return CategoryDTO.convert(categories);
        } else {
            Page<Category> categories = service.findByName(name, pagination);
            return CategoryDTO.convert(categories);
        }
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert(@RequestBody @Valid CategoryDTO dto) {

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id]").buildAndExpand(service.insert(dto).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody @Valid CategoryDTO dto) {
        return ResponseEntity.ok().body(mapper.map(service.update(dto), CategoryDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
