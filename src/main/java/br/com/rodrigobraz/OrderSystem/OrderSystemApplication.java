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

		Product prod1 = new Product(null, "Computer", 2000.00);
		Product prod2 = new Product(null, "Printer", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		Product prod4 = new Product(null, "Office Desk", 300.00);
		Product prod5 = new Product(null, "Towel", 50.00);
		Product prod6 = new Product(null, "Quilt", 200.00);
		Product prod7 = new Product(null, "TV", 1200.00);
		Product prod8 = new Product(null, "Brushcutter", 800.00);
		Product prod9 = new Product(null, "Lampshade", 100.00);
		Product prod10 = new Product(null, "Video Game", 4000.00);
		Product prod11 = new Product(null, "Shampoo", 30.00);

		cat1.getProducts().addAll(Arrays.asList(prod1,prod2,prod3));
		cat2.getProducts().addAll(Arrays.asList(prod2, prod4));
		cat3.getProducts().addAll(Arrays.asList(prod5, prod6));
		cat4.getProducts().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProducts().addAll(Arrays.asList(prod8));
		cat6.getProducts().addAll(Arrays.asList(prod9, prod10));
		cat7.getProducts().addAll(Arrays.asList(prod11));

		prod1.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategories().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategories().addAll(Arrays.asList(cat2));
		prod5.getCategories().addAll(Arrays.asList(cat3));
		prod6.getCategories().addAll(Arrays.asList(cat3));
		prod7.getCategories().addAll(Arrays.asList(cat4));
		prod8.getCategories().addAll(Arrays.asList(cat5));
		prod9.getCategories().addAll(Arrays.asList(cat6));
		prod10.getCategories().addAll(Arrays.asList(cat6));
		prod11.getCategories().addAll(Arrays.asList(cat7));

		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		productRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7,
				prod8, prod9, prod10, prod11));

		State state1 = new State(null, "Minas Gerais");
		State state2 = new State(null, "São Paulo");

		City city1 = new City(null, "Uberlandia", state1);
		City city2 = new City(null, "São Paulo", state2);
		City city3 = new City(null, "Campinas", state2);

		state1.getCities().addAll(Arrays.asList(city1));
		state2.getCities().addAll(Arrays.asList(city2, city3));

		stateRepository.saveAll(Arrays.asList(state1, state2));
		cityRepository.saveAll(Arrays.asList(city1, city2, city3));

		Customer cust1 = new Customer("Maria Silva",
				"maria@gmail.com", "11111111111", CustomerType.LEGAL_PERSON);
		cust1.getPhoneNumbers().addAll(Arrays.asList("11111111111", "22222222222"));

		CustomerAddress end1 = new CustomerAddress("Rua Flores", "300",
				"Apto 303", "Jardim", "11111111", cust1, city1);
		CustomerAddress end2 = new CustomerAddress("Avenida Matos", "105", "Sala 800",
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
