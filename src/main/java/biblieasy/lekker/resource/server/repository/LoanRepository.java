package biblieasy.lekker.resource.server.repository;


import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.Loan;
import biblieasy.lekker.resource.server.entity.LoanId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface   LoanRepository extends JpaRepository<Loan, LoanId> {


    @Transactional
    @Modifying
    @Query( value = "INSERT INTO `bookdb`.`loan` (`book_id`, `user_id`, `loan_date`, `return_date`) VALUES (:book_id, :user_id, :loan_date,:return_date)", nativeQuery = true)
    void borrowBook(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id,
            @Param("loan_date") String loan_date,
            @Param("return_date") String return_date);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Loan l WHERE (l.book_id = ?1 and l.user_id = ?2)", nativeQuery = true )
    void deleteLoanByBookidAndUserid(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE `bookdb`.`loan` SET `loan_date` = ?1 , `return_date` = ?2 WHERE (`book_id` = ?3) and (`user_id` = ?4)", nativeQuery = true )
    void updateLoan(
            @Param("loan_date") String loan_date,
            @Param("return_date") String return_date,
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);


    @Query(value = "SELECT * FROM bookdb.loan  l WHERE l.book_id = :book_id AND l.user_id = :user_id", nativeQuery = true)
    List<Map<String, Object>> findByuserIdAndbookId(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);


    List<Loan> findByBook(Book book, Sort sort);




}
