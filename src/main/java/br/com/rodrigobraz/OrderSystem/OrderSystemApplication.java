package br.com.rodrigobraz.OrderSystem;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import br.com.rodrigobraz.OrderSystem.domain.*;
import br.com.rodrigobraz.OrderSystem.domain.enums.CustomerType;
import br.com.rodrigobraz.OrderSystem.domain.enums.PaymentStatus;
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

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informatica");
		Category cat2 = new Category(null, "Escritorio");
		Category cat3 = new Category(null, "Cama mesa e banho");
		Category cat4 = new Category(null, "Jardinagem");
		Category cat5 = new Category(null, "Papelaria");
		Category cat6 = new Category(null, "Games");
		Category cat7 = new Category(null, "Eletro");
		Category cat8 = new Category(null, "Moveis");
		Category cat9 = new Category(null, "Louça");
		Category cat10 = new Category(null, "Roupas");
		Category cat11 = new Category(null, "Decoração");
		Category cat12 = new Category(null, "Perfumaria");

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Printer", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2));

		prod1.getCategories().addAll(Arrays.asList(cat1));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().addAll(Arrays.asList(cat1));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
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

		CustomerAddress end1 = new CustomerAddress(null, "Rua Flores", "300",
				"Apto 303", "Jardim", "11111111", cust1, city1);
		CustomerAddress end2 = new CustomerAddress(null, "Avenida Matos", "105", "Sala 800",
				"Centro", "11111112", cust1, city2);

		cust1.getAdresses().addAll(Arrays.asList(end1, end2));

		customerRepository.saveAll(Arrays.asList(cust1));
		adressRepository.saveAll(Arrays.asList(end1, end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		OrderBuy order1 = new OrderBuy(null, sdf.parse("30/01/2017 10:32"), cust1, end1);
		OrderBuy order2 = new OrderBuy(null, sdf.parse("10/10/2017 19:35"), cust1, end2);

		Payment pay1 = new CardPayment(null, PaymentStatus.PAYD, order1, 6);
		order1.setPayment(pay1);

		Payment pay2 = new TicketPayment(null, PaymentStatus.PENDING, order2, sdf.parse("20/10/2017 00:00"),
				null);
		order2.setPayment(pay2);

		cust1.getOrders().addAll(Arrays.asList(order1, order2));

		orderRepository.saveAll(Arrays.asList(order1, order2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));

		OrderItem item1 = new OrderItem(order1, prod1,0.00, 1, 2000.00);
		OrderItem item2 = new OrderItem(order1, prod3, 0.00, 2, 80.00);
		OrderItem item3 = new OrderItem(order2, prod2, 100.00, 1, 800.00);

		order1.getItems().addAll(Arrays.asList(item1, item2));
		order2.getItems().addAll(Arrays.asList(item3));

		prod1.getItems().addAll(Arrays.asList(item1));
		prod2.getItems().addAll(Arrays.asList(item3));
		prod3.getItems().addAll(Arrays.asList(item2));

		orderItemRepository.saveAll(Arrays.asList(item1, item2, item3));
	}
}
