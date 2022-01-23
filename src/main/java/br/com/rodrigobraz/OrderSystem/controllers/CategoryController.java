package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {

        Optional<Category> category = service.search(id);

        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody Category category, UriComponentsBuilder uri) {

        category = service.insert(category);

        URI path = uri.path("/categories/{id}").buildAndExpand(category.getId()).toUri();

        return ResponseEntity.created(path).build();
    }
}
