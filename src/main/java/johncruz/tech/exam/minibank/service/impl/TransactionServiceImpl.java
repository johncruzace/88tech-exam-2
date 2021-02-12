package johncruz.tech.exam.minibank.service.impl;

import johncruz.tech.exam.minibank.model.domain.AccountDetails;
import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.enums.TransactionType;
import johncruz.tech.exam.minibank.model.request.TransactionRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.repository.AccountDetailsRepository;
import johncruz.tech.exam.minibank.repository.BankTransactionRepository;
import johncruz.tech.exam.minibank.service.TransactionService;
import johncruz.tech.exam.minibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    public HashMap<String, Object> retrieveAccountTransactionHistory(String username){
        userService.validateLoggedUser(username);
        HashMap<String,Object> returnMap = new HashMap<String,Object>();
        List<BankTransaction> transactionList = bankTransactionRepository.findAllByUser(username);
        transactionList.sort(Comparator.comparing(BankTransaction::getId));
        returnMap.put("accountId",username);
        returnMap.put("transactionList",transactionList);
        return returnMap;
    }

    public ResponseObject transactDepositTransaction(
            String username, TransactionRequest transactionRequest) throws AccessDeniedException {

        User user = userService.retrieveSingleUser(username);
        ResponseObject responseObject = new ResponseObject();

        AccountDetails accountDetailsForResaving = userService.retrieveAccountDetails(user);
        BigDecimal runningBalance =  accountDetailsForResaving.getBalance();
        runningBalance = runningBalance.add(transactionRequest.getAmount());

        BankTransaction depositRequest = new BankTransaction();
        depositRequest.setRequestAmount(transactionRequest.getAmount());
        depositRequest.setUser(user.getUserName());
        depositRequest.setRunningBalance(runningBalance);
        depositRequest.setTransactionType(TransactionType.DEPOSIT);
        depositRequest.setCreatedBy(userService.retrieveUsernameForCreatedBy());
        depositRequest.setCreatedDate();
        bankTransactionRepository.save(depositRequest);
        accountDetailsForResaving.setBalance(runningBalance);
        accountDetailsRepository.save(accountDetailsForResaving);


        responseObject.setBorrowerName(user.getAccountName());
        responseObject.setUsername(user.getUserName());
        responseObject.setMessage("Deposit for user : " + user.getUserName() + " with amount " +
               depositRequest.getRequestAmount() + "  has been successful.");
        responseObject.setStatus(Boolean.TRUE);


        return responseObject;
    }

    public ResponseObject transactWithdrawTransaction(
            String username, TransactionRequest transactionRequest){

        userService.validateLoggedUser(username);
        User user = userService.retrieveSingleUser(username);
        ResponseObject responseObject = new ResponseObject();

        AccountDetails accountDetailsForResaving = userService.retrieveAccountDetails(user);
        BigDecimal runningBalance =  accountDetailsForResaving.getBalance();
        runningBalance = runningBalance.subtract(transactionRequest.getAmount());

        if(runningBalance.compareTo(BigDecimal.ZERO) < 0){
            throw new ArithmeticException("Balance of user: " + username + " is insufficient for withdrawal.");
        }

        BankTransaction withdrawRequest = new BankTransaction();
        withdrawRequest.setRequestAmount(transactionRequest.getAmount());
        withdrawRequest.setUser(user.getUserName());
        withdrawRequest.setRunningBalance(runningBalance);
        withdrawRequest.setTransactionType(TransactionType.WITHDRAW);
        withdrawRequest.setCreatedBy(userService.retrieveUsernameForCreatedBy());
        withdrawRequest.setCreatedDate();
        bankTransactionRepository.save(withdrawRequest);
        accountDetailsForResaving.setBalance(runningBalance);
        accountDetailsRepository.save(accountDetailsForResaving);


        responseObject.setBorrowerName(user.getAccountName());
        responseObject.setUsername(user.getUserName());
        responseObject.setMessage("Withdrawal for user : " + user.getUserName() + " with amount " +
                withdrawRequest.getRequestAmount() + "  has been successful.");
        responseObject.setStatus(Boolean.TRUE);


        return responseObject;
    }

    public ResponseObject transactTransferTransaction(
            String username, TransactionRequest transactionRequest){

        userService.validateLoggedUser(username);
        User userFrom = userService.retrieveSingleUser(username);
        User userTo = userService.retrieveSingleUser(transactionRequest.getUsernameToTransfer());
        ResponseObject responseObject = new ResponseObject();

        if(userFrom.getUserName().equals(userTo.getUserName())){
            throw new EntityExistsException("Cannot Transfer to the same account.");
        }

        AccountDetails accountDetailsFrom = userService.retrieveAccountDetails(userFrom);
        AccountDetails accountDetailsTo   = userService.retrieveAccountDetails(userTo);
        BigDecimal runningBalanceFrom =   accountDetailsFrom.getBalance();
        BigDecimal runningBalanceTo =     accountDetailsTo.getBalance();
        runningBalanceFrom = runningBalanceFrom.subtract(transactionRequest.getAmount());
        runningBalanceTo = runningBalanceTo.add(transactionRequest.getAmount());
        if(runningBalanceFrom.compareTo(BigDecimal.ZERO) < 0){
            throw new ArithmeticException("Balance of user: " + username + " is insufficient for withdrawal.");
        }
        BankTransaction transferRequestFrom = new BankTransaction();
        transferRequestFrom.setRequestAmount(transactionRequest.getAmount());
        transferRequestFrom.setUser(userFrom.getUserName());
        transferRequestFrom.setRunningBalance(runningBalanceFrom);
        transferRequestFrom.setTransactionType(TransactionType.TRANSFER_FROM);
        transferRequestFrom.setCreatedBy(userService.retrieveUsernameForCreatedBy());
        transferRequestFrom.setTransferredTo(userTo.getUserName());
        bankTransactionRepository.save(transferRequestFrom);
        accountDetailsFrom.setBalance(runningBalanceFrom);
        accountDetailsRepository.save(accountDetailsFrom);

        BankTransaction transferRequestTo = new BankTransaction();
        transferRequestTo.setRequestAmount(transactionRequest.getAmount());
        transferRequestTo.setUser(userTo.getUserName());
        transferRequestTo.setRunningBalance(runningBalanceTo);
        transferRequestTo.setTransactionType(TransactionType.TRANSFER_TO);
        transferRequestTo.setCreatedBy(userService.retrieveUsernameForCreatedBy());
        transferRequestTo.setTransferredFrom(userFrom.getUserName());
        bankTransactionRepository.save(transferRequestTo);
        accountDetailsTo.setBalance(runningBalanceTo);
        accountDetailsRepository.save(accountDetailsTo);


        responseObject.setBorrowerName(userFrom.getAccountName());
        responseObject.setUsername(userFrom.getUserName());
        responseObject.setMessage("Transfer from user : " + userFrom.getUserName() + " with amount " +
                transactionRequest.getAmount() + " to user : " +userTo.getUserName()+ " has been successful.");
        responseObject.setStatus(Boolean.TRUE);


        return responseObject;
    }



}
