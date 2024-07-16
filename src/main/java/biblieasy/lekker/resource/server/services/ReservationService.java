package biblieasy.lekker.resource.server.services;

import biblieasy.lekker.resource.server.entity.*;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.repository.ReservationRepository;
import biblieasy.lekker.resource.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepo;
    private final UserRepository usRepo;


    @Autowired
    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepo, UserRepository usRepo) {
        this.reservationRepository = reservationRepository;
        this.bookRepo = bookRepo;
        this.usRepo = usRepo;
    }


//    public void addNewReservation(ReservationId id, String reservationDate) {
//        Loan loan = new Loan(
//                loan.reserveBook(
//                        reservationRepository.findReservationByuserIdAndbookId(null, null), );
//
//        )
//
//
//    }



}
