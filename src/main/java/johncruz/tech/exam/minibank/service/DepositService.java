package johncruz.tech.exam.minibank.service;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import org.springframework.stereotype.Service;

@Service
public interface DepositService {

    ResponseObject transactDepositTransaction(BankTransaction bankTransaction);
}
