package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;
import javax.validation.constraints.NotBlank;

public class CategoryDTO {
    
    private Integer id;

    @NotBlank(message = "Must not be blank")
    @Length(min = 5, max = 80, message = "Must be between 5 and 80 letters")
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

    public Category toModel() {
        return new Category(null, name);
    }

    public Category update(Integer id, CategoryRepository repository) {
        Category category = repository.findById(id).get();
        category.setName(this.name);

        return category;
    }
}
