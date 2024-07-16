package biblieasy.lekker.resource.server.services;

import biblieasy.lekker.resource.server.entity.Book;
import biblieasy.lekker.resource.server.entity.Loan;
import biblieasy.lekker.resource.server.entity.LoanId;
import biblieasy.lekker.resource.server.repository.BookRepository;
import biblieasy.lekker.resource.server.repository.LoanRepository;
import biblieasy.lekker.resource.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoanService {



    private final LoanRepository loanRepository;
    private final BookRepository bookRepo;
    private final UserRepository usRepo;

    public LoanService(BookRepository bookRepo, UserRepository usRepo, LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
        this.bookRepo = bookRepo;
        this.usRepo = usRepo;
    }

    private List<Loan> loans = new ArrayList<>();

    public List<Loan> findAll(){
        return (List<Loan>) loanRepository.findAll();
    }

    public void save(Loan loan) {
        loanRepository.save(loan);
    }



}






