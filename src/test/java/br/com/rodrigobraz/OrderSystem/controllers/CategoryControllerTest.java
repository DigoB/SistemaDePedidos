package br.com.rodrigobraz.OrderSystem.controllers;

import br.com.rodrigobraz.OrderSystem.domain.Category;
import br.com.rodrigobraz.OrderSystem.domain.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystem.services.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryControllerTest {

    public static final String NAME = "Rodrigo";
    public static final int ID = 1;
    private Category category;
    private CategoryDTO categoryDTO;

    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryServiceImpl service;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCategory();
    }

    @Test
    void whenFindCustomerThenReturnSuccess() {
        when(service.findById(anyInt())).thenReturn(category);
        when(mapper.map(any(), any())).thenReturn(categoryDTO);

        ResponseEntity<CategoryDTO> response = controller.find(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(CategoryDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
    }

    @Test
    void whenFindListThenReturnSuccess() {
        //when(service.findList(any())).thenReturn((Page<Category>) List.of(category));
        //when(mapper.map(any(), any())).thenReturn(categoryDTO);

        /**ResponseEntity<Page<CategoryDTO>> response = controller.list();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Pageable.class, response.getBody().getClass());
        assertEquals(CategoryDTO.class, response.getBody().get().getClass());

        assertEquals(ID, response.getBody().map(categoryDTO));**/


    }

    @Test
    void insert() {
        when(service.insert(any())).thenReturn(category);

        ResponseEntity<CategoryDTO> response = controller.insert(categoryDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(service.update(categoryDTO)).thenReturn(category);
        when(mapper.map(any(), any())).thenReturn(categoryDTO);

        ResponseEntity<CategoryDTO> response = controller.update(ID, categoryDTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(CategoryDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());

        verify(service, times(1)).update(categoryDTO);
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(service).delete(anyInt());
        ResponseEntity<CategoryDTO> response = controller.delete(ID);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startCategory() {
        category = new Category(ID, NAME);
        categoryDTO = new CategoryDTO(ID, NAME);
    }
}