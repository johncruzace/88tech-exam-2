package johncruz.tech.exam.minibank.controller;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.request.TransactionRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = {"/{id}/history","/{id}/history/"})
    public ResponseEntity<HashMap<String,Object>> retrieveTransactionHistory(
            @PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(transactionService.retrieveAccountTransactionHistory(id));
    }

    @PostMapping(value = {"{id}/deposit","{id}/deposit/"})
    public ResponseEntity<ResponseObject> depositAccount(
            @PathVariable(name = "id") String id,
            @RequestBody TransactionRequest transactionRequest) throws AccessDeniedException {

        return ResponseEntity.ok().body(transactionService.transactDepositTransaction(id, transactionRequest));
    }

    @PostMapping(value = {"{id}/withdraw","{id}/withdraw/"})
    public ResponseEntity<ResponseObject> withdrawAccount(
            @PathVariable(name = "id") String id,
            @RequestBody TransactionRequest transactionRequest){

        return ResponseEntity.ok().body(transactionService.transactWithdrawTransaction(id, transactionRequest));
    }

    @PostMapping(value = {"{id}/transfer","{id}/transfer/"})
    public ResponseEntity<ResponseObject> transferAccount(
            @PathVariable(name = "id") String id,
            @RequestBody TransactionRequest transactionRequest){

        return ResponseEntity.ok().body(transactionService.transactTransferTransaction(id, transactionRequest));
    }



}
