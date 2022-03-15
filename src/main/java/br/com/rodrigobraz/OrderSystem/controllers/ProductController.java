package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.controllers.utils.UrlUtils;
import br.com.rodrigobraz.OrderSystem.domain.Product;
import br.com.rodrigobraz.OrderSystem.domain.dto.ProductDTO;
import br.com.rodrigobraz.OrderSystem.services.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Integer id) {

        Optional<Product> product = service.findById(id);

        return ResponseEntity.ok().build();
    }

    // TODO: 01/02/2022 Request returns status 200 but its not bringing the products
    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="name", defaultValue="") String name,
            @RequestParam(value="categories", defaultValue="") String categories,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="id") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nameDecoded = UrlUtils.decodeParam(name);
        List<Integer> ids = UrlUtils.decodeIntList(categories);
        Page<Product> list = service.searchList(nameDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDto);
    }
}