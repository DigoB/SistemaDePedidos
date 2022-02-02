package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.Product;
import org.springframework.data.domain.Page;

public class ProductDTO {

    private String name;

    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        name = product.getName();
        price = product.getPrice();
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
