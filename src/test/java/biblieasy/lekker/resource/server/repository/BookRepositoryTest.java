package biblieasy.lekker.resource.server.repository;


import biblieasy.lekker.resource.server.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void saveBook() {
        Book book = new Book();
        book.setTitle("First Head");
        book.setAuthor("Kathy Sierra");
        book.setPublisher("Prentice Hall");
        book.setEdition("1");
        bookRepository.save(book);
        assertNotNull(bookRepository. findAll());
    }

}
