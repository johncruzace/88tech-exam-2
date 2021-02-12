package johncruz.tech.exam.minibank.controller;

import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/users/")
    public ResponseEntity<UserResponse> retrieveAllUsers(){
        return ResponseEntity.ok().body(new UserResponse());
    }


}
