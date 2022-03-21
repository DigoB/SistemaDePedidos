package br.com.rodrigobraz.OrderSystem.services.impl;

import br.com.rodrigobraz.OrderSystem.domain.City;
import br.com.rodrigobraz.OrderSystem.domain.Customer;
import br.com.rodrigobraz.OrderSystem.domain.CustomerAddress;
import br.com.rodrigobraz.OrderSystem.domain.State;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystem.domain.dto.CustomerPostDTO;
import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import br.com.rodrigobraz.OrderSystem.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImplTest {

    public static final String NAME = "Rodrigo";
    public static final String EMAIL = "rodrigo@email.com";
    public static final String DOCUMENT = "111.111.111-11";
    public static final int CITY_ID = 1;
    public static final CustomerType CUSTOMER_TYPE = CustomerType.toEnum(CITY_ID);
    public static final String CITY_NAME = "São Paulo";
    public static final State STATE = null;
    public static final String STREET = "Rua A";
    public static final String NUMBER = "100";
    public static final String COMPLEMENT = "Apto 100";
    public static final String DISTRICT = "Bairro A";
    public static final String ZIP_CODE = "00000-000";
    public static final String PHONE1 = "11111111111";
    public static final String PHONE2 = "22222222222";
    public static final String PHONE3 = "33333333333";
    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository repository;
    @Mock
    private ModelMapper mapper;
    private Customer customer;
    private CustomerDTO customerDTO;
    private CustomerPostDTO customerPostDTO;
    private Optional<Customer> optionalCustomer;
    private City city;
    private CustomerAddress address;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCustomer();
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void searchList() {
    }

    @Test
    void findByName() {
    }

    @Test
    void toModel() {
    }

    private void startCustomer() {
        customer = new Customer(NAME, EMAIL, DOCUMENT, CUSTOMER_TYPE);
        city = new City(CITY_ID, CITY_NAME, STATE);
        address = new CustomerAddress(STREET, NUMBER, COMPLEMENT, DISTRICT, ZIP_CODE, customer, city);
        customer.getAdresses().add(address);

        customer.getPhoneNumbers().add(PHONE1);
        if (PHONE2 != null) {
            customer.getPhoneNumbers().add(PHONE2);
        }
        if (PHONE3 != null) {
            customer.getPhoneNumbers().add(PHONE3);
        }
    }
}