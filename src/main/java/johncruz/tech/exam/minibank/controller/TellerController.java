package johncruz.tech.exam.minibank.controller;

import johncruz.tech.exam.minibank.model.domain.AccountDetails;
import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.request.AccountDetailsRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teller")
public class TellerController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = {"/account/","/account"})
    public ResponseEntity<List<User>> retrieveAllUsers(){
        return ResponseEntity.ok().body(userService.retrieveAllUsers());
    }

    @GetMapping(value = {"/account/{id}","/account/{id}/"})
    public ResponseEntity<User> retrieveUserDetails(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.retrieveSingleUser(id));
    }

    @PostMapping(value = {"/account","/account/"})
    public ResponseEntity<ResponseObject> createNewUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createNewUser(user));
    }

    @DeleteMapping(value = {"/account/{id}","/account/{id}/"})
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping(value = {"/account/{id}/bank-account","/account/{id}/bank-account/"})
    public ResponseEntity<ResponseObject> openNewAccount(
            @PathVariable(name = "id") String id,
            @RequestBody AccountDetailsRequest accountDetails){
        return ResponseEntity.ok().body(userService.openAccountForUser(id,accountDetails));
    }


    @DeleteMapping(value = {"/account/{id}/bank-account","/account/{id}/bank-account/"})
    public ResponseEntity<ResponseObject> deleteBankAccount(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.deleteBankAccount(id));
    }


}
