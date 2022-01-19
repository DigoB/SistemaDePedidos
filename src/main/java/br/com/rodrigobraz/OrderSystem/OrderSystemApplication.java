package br.com.rodrigobraz.OrderSystem;

import java.util.Arrays;

import br.com.rodrigobraz.OrderSystem.domain.*;
import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import br.com.rodrigobraz.OrderSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderSystemApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Printer", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2));

		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		Customer cust1 = new Customer(null, "Maria Silva",
				"maria@gmail.com", "11111111111", CustomerType.LEGAL_PERSON);
		cust1.getPhoneNumbers().addAll(Arrays.asList("11111111111", "22222222222"));

		Endereco end1 = new Endereco(null, "Rua Flores", "300",
				"Apto 303", "Jardim", "11111111", cust1, city1);
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800",
				"Centro", "11111112", cust1, city2);

		cust1.getAdresses().addAll(Arrays.asList(end1, end2));

		customerRepository.saveAll(Arrays.asList(cust1));
		adressRepository.saveAll(Arrays.asList(end1, end2));
	}

}
