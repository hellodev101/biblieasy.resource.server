package biblieasy.lekker.resource.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import static javax.persistence.GenerationType.SEQUENCE;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

//@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity(name="User")
@Table(name="user")
public class User {

//    @Id
//    @GeneratedValue(strategy= GenerationType.AUTO)
//    private long userId;

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "userId",
            updatable = false
    )
    private long userId;

    private String name;
    private String username;
    private String password;
    //load user and load role

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Role> roles = new ArrayList<>();


    @OneToMany(
            orphanRemoval = true,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            fetch = FetchType.LAZY,
            //mapped in Loan
            mappedBy="user")
    private List<Loan> loans = new ArrayList<>();


    public User() {

    }

    public User(String name, String password, String userName) {
        super();
        this.username = userName;
        this.password = password;
        this.name = name;
    }

    public User(long userId, String name, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", loans=" + loans +
                '}';
    }


    public void addLoan(Loan loan) {
        if (!loans.contains(loan)) {
            loans.add(loan);
        }
    }

        public void removeLoan(Loan loan) {
            loans.remove(loan);
        }




//        public void borrowBook(Book book){
//        book
//        book.getTheUser().add(this);
//        }


//        public static void addBorrowBook(Loan loan){
//        if(!loans.contains(loan)){
//
//
//            loans.add(loan);
//            loan.setUser(this);
//        }
//    }
//
//    public void removeBorrowBook(Loan loan){
//        if(!this.loans.contains(loan)){
//            this.loans.add(loan);
//            loan.setUser(null);
//        }
//    }



}
