package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import org.springframework.data.domain.Page;

public class CategoryDTO {

    private Integer id;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Page<CategoryDTO> convert(Page<Category> categories) {
        return categories.map(CategoryDTO::new);
    }
}
