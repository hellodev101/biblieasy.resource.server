package biblieasy.lekker.resource.server.repository;


import biblieasy.lekker.resource.server.entity.Reservation;
import biblieasy.lekker.resource.server.entity.ReservationId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;


@Repository
public interface ReservationRepository extends CrudRepository<Reservation, ReservationId> {



    @Transactional
    @Modifying
    @Query( value = "INSERT INTO `bookdb`.`reservation` (`book_id`, `user_id`, `reservation_date`) VALUES (:book_id, :user_id, :reservation_date)", nativeQuery = true)
    void reserveBook(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id,
            @Param("reservation_date") String reservation_date);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM reservation r WHERE (r.book_id = ?1 and r.user_id = ?2)", nativeQuery = true )
    void deleteReserveByBookidAndUserid(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);


    @Transactional
    @Modifying
    @Query(value = "UPDATE `bookdb`.`reservation` SET `reservation_date` = :reservation_date WHERE (`book_id` = :book_id) and (`user_id` = :user_id);", nativeQuery = true )
    void updateReservation(
            @Param("reservation_date") String reservation_date,
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);



    @Query(value = "SELECT * FROM bookdb.reservation  r WHERE r.book_id = :book_id AND r.user_id = :user_id", nativeQuery = true)
    List<Map<String, Object>> findReservationByuserIdAndbookId(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);

    @Transactional
    @Modifying
    @Query( value = "INSERT INTO reservation(book_id, user_id, reservation_date) \n" +
            "SELECT \n" +
            "   book_id,\n" +
            "   user_id,\n" +
            "   return_date\n" +
            "FROM loan  \n" +
            "WHERE  (`book_id` = ?1) and (`user_id` = ?2) " , nativeQuery = true)
    void copyReserveBook(
            @Param("book_id") Long book_id,
            @Param("user_id") Long user_id);


}
