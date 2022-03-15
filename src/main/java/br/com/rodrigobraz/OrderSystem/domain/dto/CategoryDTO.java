package br.com.rodrigobraz.OrderSystem.domain.dto;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
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

    public static Page<CategoryDTO> convert(Page<Category> categories) {
        return categories.map(CategoryDTO::new);
    }
}
