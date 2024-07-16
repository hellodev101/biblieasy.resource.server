package biblieasy.lekker.resource.server.entity;


import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;


@NoArgsConstructor
@Entity(name="Book")
@Table(name="book")
public class Book implements Serializable {

//    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
//    private long bookId;

@Id
@SequenceGenerator(
        name = "book_sequence",
        sequenceName = "book_sequence",
        allocationSize = 1
)
@GeneratedValue(
        strategy = SEQUENCE,
        generator = "book_sequence"
)
@Column(
        name = "bookId",
        updatable = false
)
    private long bookId;

    private String title;
    private String author;
    private String publisher;
    private String edition;


    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            //mapped in Loan
            mappedBy="book")
//    @Fetch(FetchMode.JOIN)
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Set<Loan> loans;
    private List<Loan> loans = new ArrayList<>();






    public Book(String title, String author, String publisher, String edition) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
    }

    public Book(long bookId, String title, String author, String publisher, String edition) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }

    public void addLoan(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
    }

    public void removeLoan(Loan loan) {
        loans.remove(loan);
    }



    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", edition='" + edition + '\'' +
                ", loans=" + loans +
                '}';
    }
}