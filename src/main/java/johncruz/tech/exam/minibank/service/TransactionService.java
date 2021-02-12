package johncruz.tech.exam.minibank.service;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.request.TransactionRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public interface TransactionService {

    HashMap<String,Object> retrieveAccountTransactionHistory(String id);

    ResponseObject transactDepositTransaction(String id, TransactionRequest bankTransaction) throws AccessDeniedException;

    ResponseObject transactWithdrawTransaction(String id, TransactionRequest bankTransaction);

    ResponseObject transactTransferTransaction(String id, TransactionRequest bankTransaction);
}
