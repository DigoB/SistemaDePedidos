package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystem.services.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystem.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceImplTest {

    public static final int ID = 1;
    public static final String NAME = "Escritório";

    @InjectMocks
    private CategoryServiceImpl service;

    @Mock
    private CategoryRepository repository;
    @Mock
    private ModelMapper mapper;
    private Category category;
    private CategoryDTO categoryDTO;
    private Optional<Category> optionalCategory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategory();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalCategory);

        Category response = service.findById(ID);
        assertNotNull(response);
        assertEquals(Category.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
    }

    @Test
    void whenFindByIdThenReturnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(
                new ObjectNotFoundException("Object not found! Id: " + ID + " type: " + Category.class.getName()));
        try {
            service.findById(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals("Object not found! Id: " + ID + " type: " + Category.class.getName(), ex.getMessage());
        }
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        when(repository.findById(anyInt())).thenReturn(optionalCategory);
        doNothing().when(repository).deleteById(anyInt());
        service.delete(ID);

        verify(repository, times(1)).deleteById(ID);
    }

    @Test
    void mustReturnDataIntegrityExceptionWhenUpdate() {
        when(repository.findById(anyInt())).thenReturn(optionalCategory);

        try {
            service.update(categoryDTO);
        } catch (Exception e) {
            assertEquals(DataIntegrityException.class, e.getClass());
            assertEquals("Not possible delete a customer that has orders", e.getMessage());
        }
    }

    @Test
    void findList() {
    }

    @Test
    void findByName() {
    }

    private void startCategory() {
        category = new Category(ID, NAME);
        categoryDTO = new CategoryDTO(ID, NAME);
        optionalCategory = Optional.of(new Category(ID, NAME));
    }
}