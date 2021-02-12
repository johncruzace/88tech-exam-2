package johncruz.tech.exam.minibank.controller;

import johncruz.tech.exam.minibank.model.domain.BankTransaction;
import johncruz.tech.exam.minibank.model.request.TransactionRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @PostMapping(value = {"{id}/deposit","{id}/deposit/"})
    public ResponseEntity<ResponseObject> depositAccount(
            @PathVariable(name = "id") String id,
            @RequestBody TransactionRequest transactionRequest){

        return ResponseEntity.ok().body(new ResponseObject());
    }

}
