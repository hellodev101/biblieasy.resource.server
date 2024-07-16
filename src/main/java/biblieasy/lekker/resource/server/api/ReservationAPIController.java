package biblieasy.lekker.resource.server.api;

import biblieasy.lekker.resource.server.entity.Reservation;
import biblieasy.lekker.resource.server.repository.ReservationRepository;
import biblieasy.lekker.resource.server.services.ReservationService;
import biblieasy.lekker.resource.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReservationAPIController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ReservationService reservationService;

    @Autowired
    UserService userService;

//force one
    @PostMapping("/reserveBook")
    public void reserveBook (@RequestParam Long bookId,
                             @RequestParam Long userId,
                             @RequestParam String reservationDate){
        reservationRepository.reserveBook(bookId, userId, reservationDate);   }

//    @PostMapping("/copy/reserveBook")
//    public void copyReserveBook (@RequestParam Long bookId,
//                                 @RequestParam Long userId){
//        reservationRepository.copyReserveBook(bookId, userId);   }


    @DeleteMapping(path ="/delete/reservation")
    void deleteReserveByBookidAndUserid (@RequestParam Long bookId,
                     @RequestParam Long userId) {
        reservationRepository.deleteReserveByBookidAndUserid(bookId, userId);
    }

    @PutMapping(path ="/update/reservation")
    public void updateReservation(
            @RequestParam String reservationDate,
            @RequestParam Long bookId,
            @RequestParam Long userId){
        reservationRepository.updateReservation(reservationDate, bookId, userId);
    }


    @RequestMapping(value ="/reservations", method = RequestMethod.GET)
    public List<Map<String, Object>> fetch (@RequestParam Long bookId,
                                            @RequestParam Long userId){
        return reservationRepository.findReservationByuserIdAndbookId(bookId, userId);
    }








}
