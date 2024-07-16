package biblieasy.lekker.resource.server.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name ="Reservation")
@Table(name = "reservation")
public class Reservation {


    @EmbeddedId
    private ReservationId id;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.EAGER, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id",
    referencedColumnName = "userId",
    foreignKey = @ForeignKey(name = "reserve_user_fk"))
    private User user;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.EAGER, optional = false)
    @MapsId("bookId")
    @JoinColumn(name = "book_id",
            referencedColumnName = "bookId",
            foreignKey = @ForeignKey(name = "reserve_book_fk"))
    private Book book;

    @Column(
            name = "reservation_date",
            nullable = false
//            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate reservationDate;

    public Reservation(ReservationId id, User user, Book book, LocalDate reservationDate) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
    }

    public Reservation(ReservationId id, LocalDate reservationDate) {
        this.id = id;
        this.reservationDate = reservationDate;
    }

    public Reservation() {
    }

    public ReservationId getId() {
        return id;
    }

    public void setId(ReservationId id) {
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", book=" + book +
                ", reservationDate=" + reservationDate +
                '}';
    }


}
