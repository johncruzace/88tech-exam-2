package johncruz.tech.exam.minibank.controller;

import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.request.AccountDetailsRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/teller")
public class TellerController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = {"/account-history/","/account-history"})
    public ResponseEntity<List<HashMap<String,Object>>> retrieveAllAccountHistory(){
        return ResponseEntity.ok().body(userService.retrieveAllUserHistory());
    }

    @GetMapping(value = {"/user/","/user"})
    public ResponseEntity<List<User>> retrieveAllUsers(){
        return ResponseEntity.ok().body(userService.retrieveAllUsers());
    }

    @GetMapping(value = {"/user/{id}","/user/{id}/"})
    public ResponseEntity<User> retrieveUserDetails(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.retrieveSingleUser(id));
    }

    @PostMapping(value = {"/user","/user/"})
    public ResponseEntity<ResponseObject> createNewUser(@RequestBody User user){
        return ResponseEntity.ok().body(userService.createNewUser(user));
    }

    @DeleteMapping(value = {"/user/{id}","/user/{id}/"})
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }

    @PostMapping(value = {"/user/{id}/bank-account","/user/{id}/bank-account/"})
    public ResponseEntity<ResponseObject> openNewAccount(
            @PathVariable(name = "id") String id,
            @RequestBody AccountDetailsRequest accountDetails){
        return ResponseEntity.ok().body(userService.openAccountForUser(id,accountDetails));
    }


    @DeleteMapping(value = {"/user/{id}/bank-account","/user/{id}/bank-account/"})
    public ResponseEntity<ResponseObject> deleteBankAccount(@PathVariable(name = "id") String id){
        return ResponseEntity.ok().body(userService.deleteBankAccount(id));
    }


}
