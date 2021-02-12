package johncruz.tech.exam.minibank.service;

import johncruz.tech.exam.minibank.model.domain.DepositRequest;
import johncruz.tech.exam.minibank.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private DepositRepository depositRepository;

    public void testCreateDeposit(){
        DepositRequest depositRequest = new DepositRequest();
        depositRequest.setRequestAmount(new BigDecimal("1000"));
        depositRequest.setUser("JOHN");
        depositRequest.setRunningBalance(new BigDecimal("1000"));
        depositRequest.setRequestDateTime(LocalDateTime.now(Clock.systemUTC()));
        depositRepository.save(depositRequest);
    }

}
