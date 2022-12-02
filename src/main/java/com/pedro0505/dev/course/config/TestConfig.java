package com.pedro0505.dev.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.pedro0505.dev.course.entities.Category;
import com.pedro0505.dev.course.entities.Order;
import com.pedro0505.dev.course.entities.User;
import com.pedro0505.dev.course.entities.enums.OrderStatus;
import com.pedro0505.dev.course.repositories.CategoryRepository;
import com.pedro0505.dev.course.repositories.OrderRepository;
import com.pedro0505.dev.course.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		Category cat1 = new Category(null, "Electronics");
		Category cat2 = new Category(null, "Books");
		Category cat3 = new Category(null, "Computers"); 

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

		this.categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		this.userRepository.saveAll(Arrays.asList(u1, u2));
		this.orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
