package biblieasy.lekker.resource.server.api;

import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.Loan;
import biblieasy.lekker.resource.server.entity.LoanId;
import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.services.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoanAPIController {


    @Autowired
    LoanRepository loanRepository;

    @Autowired
    LoanService loanService;


    @PostMapping("/borrowBook")
    public void borrowBook(@RequestParam Long bookId,
                           @RequestParam Long userId,
                           @RequestParam String loanDate,
                           @RequestParam String returnDate) {
        loanRepository.borrowBook(bookId, userId, loanDate, returnDate);
    }

    @RequestMapping(value ="/loans", method = RequestMethod.GET)
    public List<Map<String, Object>> fetch (@RequestParam Long bookId,
                                            @RequestParam Long userId){
        return loanRepository.findByuserIdAndbookId(bookId, userId);
    }


    @PutMapping(path ="/update/loan")
    public void updateLoan(
            @RequestParam(required = false) String loanDate,
            @RequestParam(required = false) String returnDate,
            @RequestParam Long bookId,
            @RequestParam Long userId){
        loanRepository.updateLoan(loanDate, returnDate, bookId, userId);
    }

    @DeleteMapping(path ="/delete/loan")
    void deleteLoan (@RequestParam Long bookId,
                     @RequestParam Long userId) {
        loanRepository.deleteLoanByBookidAndUserid(bookId, userId);
    }



    }//end of class


    



