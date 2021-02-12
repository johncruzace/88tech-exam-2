package johncruz.tech.exam.minibank.service;

import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
   List<User> retrieveAllUsers();

   User retrieveSingleUser(String username);

   ResponseObject createNewUser(User newUser);

   ResponseObject deleteUser(String username);

   Optional<User> retrieveUser(String username);
}
