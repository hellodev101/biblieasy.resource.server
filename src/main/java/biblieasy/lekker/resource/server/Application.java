package biblieasy.lekker.resource.server;

//import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.entity.*;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.repository.ReservationRepository;
import biblieasy.lekker.resource.server.repository.UserRepository;
import biblieasy.lekker.resource.server.services.BookService;
import biblieasy.lekker.resource.server.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
@EnableConfigServer
@SpringBootApplication
public class Application {



	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(LoanRepository loanRepository, UserRepository userRepository, UserService userService, BookRepository bookRepository, UserRepository usRepo, BookService bookService, ReservationRepository reservationRepository) {
		return args -> {


			//save users
			userService.saveUser(new User(1L, "Soraya Preechadech", "soraya", "password"));
			userService.saveUser(new User(2L, "Johny Depp", "johny", "password"));
			userService.saveUser(new User(3L, "Justin Bieber", "justin", "password"));


			// save books
			Book book1 = new Book(1L,"Core Java Volume I – Fundamentals", "Cay S. Horstmann", "Prentice Hall","11th Edition");
			bookRepository.save(book1);

			Book book2 = new Book(2L,"Effective Java", "Joshua Bloch", "Addison Wesley","3rd Edition");
			bookRepository.save(book2);

			Book book3 = new Book(3L,"Java: A Beginner’s Guide", "Herbert Schildt", "McGraw-Hill Education","8th Edition");
			bookRepository.save(book3);

			Book book4 = new Book(4L,"Java - The Complete Reference", "Herbert Schildt", "McGraw Hill Education","11th Edition");
			bookRepository.save(book4);

			Book book5 = new Book(5L,"Head First Java", "Kathy Sierra & Bert Bates", "Shroff/O’Reilly","2nd Edition");
			bookRepository.save(book5);

			Book book6 = new Book(6L,"Java Concurrency in Practice", "Joshua Bloch, Joseph Bowbeer, David Holmes",
					"1st Edition","Addison-Wesley Professional");
			bookRepository.save(book6);

			Book book7 = new Book(7L,"Test-Driven: TDD and Acceptance TDD for Java Developers", "Lasse Koskela", "Manning Publications","1st Edition");
			bookRepository.save(book7);

			Book book8 = new Book(8L,"Head First Object-Oriented Analysis Design", "Brett D. McLaughlin, Gary Pollice & David West",
					"Shroff/O’Reilly","1st Edition");
			bookRepository.save(book8);

			Book book9 = new Book(9L,"Java Performance: The Definite Guide", "Scott Oaks", "Shroff/O’Reilly","1st Edition");
			bookRepository.save(book9);

			Book book10 = new Book(10L,"Head First Design Patterns", "Eric Freeman & Elisabeth Robson with Kathy Sierra & Bert Bates",
					"Shroff/O’Reilly","10th Anniversary Edition");
			bookRepository.save(book10);



			// set child reference
			loanRepository.borrowBook(
					 1L, 1L, "2022-01-10", "2022-01-15");

//			reservationRepository.reserveBook(
//					1L, 1L, "2022-01-16");


			userService.saveRole(new Role(1L, "USER"));
			userService.saveRole(new Role(2L, "LIBRARIAN"));
			userService.saveRole(new Role(3L, "ADMIN"));

			userService.addRoleToUser("soraya", "USER");
			userService.addRoleToUser("soraya", "LIBRARIAN");
			userService.addRoleToUser("soraya", "ADMIN");
			userService.addRoleToUser("johny", "LIBRARIAN");
			userService.addRoleToUser("johny", "USER");
			userService.addRoleToUser("justin", "USER");

			PageRequest pageRequest = PageRequest.of(
					0,
					5,
					Sort.by("title").ascending());
			Page<Book> page = bookRepository.findAll(pageRequest);
			System.out.println(page);
//
		};
	}


}




