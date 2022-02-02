package br.com.rodrigobraz.OrderSystem.services;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.Product;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.repositories.ProductRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Product> findById(Integer id) {
        Optional<Product> possibleProduct = productRepository.findById(id);
        if (possibleProduct.isEmpty()) {
            throw new ObjectNotFoundException("Object not found! Id: " + id + " Type: " + Product.class.getName());
        }
        return possibleProduct;
    }

    public Page<Product> searchList(String name, List<Integer> ids, Integer page, Integer linesPerPage,
                                String orderBy, String direction) {

        Pageable pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);
        return productRepository.searchList(name, categories, pageRequest);

    }
}
