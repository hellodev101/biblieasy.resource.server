package biblieasy.lekker.resource.server.api;

import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookAPIController {


    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookService bookService;



    @PostMapping("/book/save")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/book/save").toUriString());
        return ResponseEntity.created(uri).body(bookRepository.save(book));
    }


    @GetMapping("/books")
    public ResponseEntity<List<Book>> book(){
        return ResponseEntity.ok().body(bookRepository.findAll());    }


    @RequestMapping(value ="/book", method = RequestMethod.GET)
    public Book fetch (@RequestParam String title)  {
        return (Book) bookRepository.findByTitle(title);
    }

    @DeleteMapping(path ="/delete/book/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
    }

    @PutMapping(path = "/update/book/{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String edition){
        bookService.updateBook(bookId, title, author,publisher, edition);

    }

    @RequestMapping(value = "/book/pageable", method = RequestMethod.GET)
    Page<Map<String, Object>> findAllBooks(Pageable pageable, Long bookId) {
        return bookRepository.pageAllBooks(bookId, pageable);
    }


}




