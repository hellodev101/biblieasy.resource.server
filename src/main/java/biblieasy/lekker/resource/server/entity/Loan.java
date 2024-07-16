package biblieasy.lekker.resource.server.entity;


import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor

@Entity(name ="Loan")
@Table(name = "loan")
public class Loan implements Serializable {

    @EmbeddedId
    private LoanId id;

    @ManyToOne(cascade={ CascadeType.MERGE, CascadeType.REMOVE,CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id",
            referencedColumnName = "userId",
            foreignKey = @ForeignKey(name = "loan_user_fk"))
    private User user;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE,CascadeType.PERSIST},
            fetch = FetchType.LAZY,optional = false)
    @MapsId("bookId")
    @JoinColumn(name = "book_id", nullable = false,
            referencedColumnName = "bookId",
    foreignKey = @ForeignKey(name = "loan_book_fk"))
    private Book book;





    @Column(
            name = "loan_date"
//            nullable = false,
//            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate loanDate;

    @Column(
            name = "return_date"
//            nullable = false,
//            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate returnDate;

    public Loan(LoanId id, User user, Book book, LocalDate loanDate, LocalDate returnDate) {

        this.id = id;
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public Loan(LoanId id, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }





    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LoanId getId() {
        return id;
    }

    public void setId(LoanId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

