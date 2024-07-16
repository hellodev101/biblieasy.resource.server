package biblieasy.lekker.resource.server;

import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.Loan;
import biblieasy.lekker.resource.server.entity.LoanId;
import biblieasy.lekker.resource.server.entity.User;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JunitTest {

//    @Autowired
//    LoanRepository loanRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    BookRepository bookRepository;


//    @Test
//    public void saveUser() {
//        User user = new User();
//        user.setUserId(1L);
//        user.setName("Konan");
//        user.setUsername("konan");
//        user.setPassword("password");
//        userService.saveUser(user);
//        assertNotNull(userService.getUsers());
//    }
//
//    @Test
//    public void saveBook() {
//        Book book = new Book();
//        book.setBookId(1L);
//        book.setTitle("First Head");
//        book.setAuthor("Kathy Sierra");
//        book.setPublisher("Prentice Hall");
//        book.setEdition("1");
//        bookRepository.save(book);
//        assertNotNull(bookRepository.findAll());
//    }

    //    @Test
//    void borrowBook() {
//        loanRepository.save(new Loan(new LoanId(1L, 1L),
//                LocalDateTime.now(),LocalDateTime.now().plusDays(5)));
//    }
// Insert a new Employee in the database
//    @Test
//    void borrowBook() {
//        Loan loan = new Loan(new LoanId(1L, 1L),
//                LocalDateTime.now(),
//                LocalDateTime.now().plusDays(5));
//
//        loanRepository.save(loan);
//    }
}