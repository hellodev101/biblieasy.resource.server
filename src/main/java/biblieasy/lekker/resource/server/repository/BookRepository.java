package biblieasy.lekker.resource.server.repository;

import biblieasy.lekker.resource.server.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface BookRepository extends PagingAndSortingRepository <Book, Long> {

    //find book by title
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    Optional<Book> findBookByTitle(String title);



    @Query(value = "SELECT * FROM bookdb.book b WHERE b.title = :title OR b.author = :author OR b.edition = :edition OR b.publisher = :publisher", nativeQuery = true)
    List<Book> searchBook (
            @Param("title") String title,
            @Param("author") String author,
            @Param("edition") String edition,
            @Param("publisher") String publisher);

    List<Book> findAll();

    Book findByTitle(String title);

    Optional<Book> findBookByAuthor(String author);

    @Query(value = "SELECT * FROM Book ",
            countQuery = "SELECT count(*) FROM Book  ORDER BY bookId",
            nativeQuery = true)
    Page<Map<String, Object>> pageAllBooks(Long bookId, Pageable pageable);



}



