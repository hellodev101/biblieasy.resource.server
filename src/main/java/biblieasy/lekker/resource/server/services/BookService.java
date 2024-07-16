package biblieasy.lekker.resource.server.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.User;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.repository.RoleRepo;
import biblieasy.lekker.resource.server.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BookService{


    private final BookRepository bookRepo;
    private final UserRepository usRepo;
//    private final RoleRepo roleRepo;

    @Autowired
    public BookService(BookRepository bookRepo, UserRepository usRepo, RoleRepo roleRepo) {
        this.bookRepo = bookRepo;
        this.usRepo = usRepo;
//        this.roleRepo = roleRepo;
    }

    public void save(Book book) {
        bookRepo.save(book);
    }

    public void saveById(Long bookId) {
        bookRepo.save(bookRepo.findById(bookId).get());
    }

    public List<Book> findAll(){
        return (List<Book>) bookRepo.findAll();
    }

    public Book findById(long bookId) {
        Book book = bookRepo.findById(bookId).get();
        return book;
    }

    public List<Book> searchBooks(String title, String author){

        List<Book> searchedBooks = new ArrayList<Book>();

        if (title != null && author == null) {
            searchedBooks = getByTitle(title);
        } else if (title == null && author != null) {
            searchedBooks = getByAuthor(author);
        } else if (title != null && author != null) {
            searchedBooks = getByTitleAndAuthor(title, author);
        }

        return searchedBooks;
    }

    public List<Book> getByTitle(String title){
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepo.findAll()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                books.add(book);
            }
        }
        return books;
    }

    public List<Book> getByAuthor(String author){
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepo.findAll()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                books.add(book);
            }
        }
        return books;
    }



    public List<Book> getByTitleAndAuthor(String title, String author){
        List<Book> books = new ArrayList<>();
        for (Book book : bookRepo.findAll()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase()) &&
                    book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                books.add(book);
            }
        }
        return books;
    }

    public void deleteById(long bookId) {
        bookRepo.deleteById(bookId);
    }


    public void addNewBook(Book book) {
        Optional<Book> BookOptional = bookRepo
                .findBookByTitle(book.getTitle());
        if(BookOptional.isPresent()){
            throw new IllegalStateException("Already have book with this name in the repository");
        }
        bookRepo.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepo.existsById(bookId);
        if(!exists){
            throw new IllegalStateException(
                    "book with id " + bookId + " does not exists");
        }
        bookRepo.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author, String publisher, String edition) {
        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new IllegalStateException(
                        "book with id " + bookId + " does not exist"));
        if (author != null &&
        author.length() > 0 &&
        !Objects.equals(book.getAuthor() , author)){
            book.setAuthor(author);
        }

        if (publisher != null &&
                publisher.length() > 0 &&
                !Objects.equals(book.getPublisher() , publisher)){
            book.setPublisher(publisher);
        }

        if (edition != null &&
                edition.length() > 0 &&
                !Objects.equals(book.getEdition(), edition)){
            book.setEdition(edition);
        }

        if(title != null &&
        title.length() > 0 &&
        !Objects.equals(book.getTitle(), title)){
            Optional<Book> bookOptional = bookRepo
                    .findBookByTitle(title);
            if(bookOptional.isPresent()){
                throw new IllegalStateException(("this title is already exist"));
            }
            book.setTitle(title);
        }
    }





}
