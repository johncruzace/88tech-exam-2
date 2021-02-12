package johncruz.tech.exam.minibank.service;

import johncruz.tech.exam.minibank.model.domain.AccountDetails;
import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
   List<User> retrieveAllUsers();

   User retrieveSingleUser(String username);

   List<HashMap<String,Object>> retrieveAllUserHistory();

   ResponseObject createNewUser(User newUser);

   ResponseObject deleteUser(String username);

   Optional<User> retrieveUser(String username);

   Boolean validateLoggedUser(String username);

   String retrieveUsernameForCreatedBy();

   AccountDetails retrieveAccountDetails(User user);
}
