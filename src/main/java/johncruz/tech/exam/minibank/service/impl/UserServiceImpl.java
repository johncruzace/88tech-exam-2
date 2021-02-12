package johncruz.tech.exam.minibank.service.impl;

import johncruz.tech.exam.minibank.model.domain.AccountDetails;
import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.request.AccountDetailsRequest;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.repository.AccountDetailsRepository;
import johncruz.tech.exam.minibank.repository.UserRepository;
import johncruz.tech.exam.minibank.service.TransactionService;
import johncruz.tech.exam.minibank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountDetailsRepository accountDetailsRepository;

    @Autowired
    private TransactionServiceImpl transactionService;

    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    public List<HashMap<String,Object>> retrieveAllUserHistory(){
        List<User> userList = userRepository.findAll();
        List<HashMap<String,Object>> returnList = new ArrayList<HashMap<String,Object>>();
        for(User user: userList){
            returnList.add(transactionService.retrieveAccountTransactionHistory(user.getUserName()));
        }
        return returnList;
    }

    public User retrieveSingleUser(String username){
        Optional<User> user = retrieveUser(username);
        if(user.isPresent()){
            return user.get();
        } else {
            throw new EntityNotFoundException("User with username: " + username + " not found");
        }
    }

    public ResponseObject createNewUser(User newUser){

        Optional<User> user = retrieveUser(newUser.getUserName());
        ResponseObject userResponse = new ResponseObject();
        if(user.isPresent()){
            throw new EntityExistsException("User with username: " + newUser.getUserName() + " already exists");
        } else {
            newUser.setPassword(newUser.getUserName());
            newUser.setActive(Boolean.TRUE);
            userRepository.save(newUser);
            userResponse.setUsername(newUser.getUserName());
            userResponse.setBorrowerName(newUser.getAccountName());
            userResponse.setStatus(newUser.isActive());
            userResponse.setMessage("Successfully created new account with username : " + newUser.getUserName());
        }
        return userResponse;
    }

    public ResponseObject deleteUser(String username){
        User user = retrieveSingleUser(username);
        ResponseObject userResponse = new ResponseObject();
        userResponse.setUsername(user.getUserName());
        userResponse.setBorrowerName(user.getAccountName());
        userResponse.setMessage("Account with username : " + user.getUserName() + " has been deleted.");
        userResponse.setStatus(Boolean.TRUE);
        userRepository.delete(user);

        return userResponse;
    }

    public ResponseObject openAccountForUser(String username, AccountDetailsRequest accountDetails){

        User user = retrieveSingleUser(username);
        AccountDetails detailsToSave = new AccountDetails();

        Optional<AccountDetails> checkAccountDetails = accountDetailsRepository.findByUser(user);
        if(checkAccountDetails.isPresent()){
            throw new EntityExistsException("User already has an existing account");
        }
        detailsToSave.setAccountId(user.getUserName());
        detailsToSave.setBalance(accountDetails.getAmount());
        detailsToSave.setUser(user);

        accountDetailsRepository.save(detailsToSave);

        ResponseObject responseObject = new ResponseObject();
        responseObject.setBorrowerName(user.getAccountName());
        responseObject.setUsername(user.getUserName());
        responseObject.setMessage("Successfully opened new account for user : " + user.getUserName());
        responseObject.setStatus(Boolean.TRUE);
        return responseObject;
    }

    public ResponseObject deleteBankAccount(String username){
        ResponseObject responseObject = new ResponseObject();
        Optional<AccountDetails> accountDetails = accountDetailsRepository.findByUser(retrieveSingleUser(username));
        if( accountDetails.isPresent()){
            accountDetailsRepository.delete(accountDetails.get());
            responseObject.setUsername(username);
            responseObject.setMessage("Banking account of user : " + username + " has been closed.");
            responseObject.setStatus(Boolean.TRUE);
        } else {
           throw new EntityNotFoundException("User : "+ username + " has no accounts opened.");
        }

        return responseObject;
    }


    public Boolean validateLoggedUser(String username){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = retrieveSingleUser(currentUserName);
            if(user.getRole().equalsIgnoreCase("teller")){
                return Boolean.TRUE;
            } else if(!user.getRole().equalsIgnoreCase("teller") &&
                !user.getUserName().equalsIgnoreCase(username) ){

                throw new AccessDeniedException("Account cannot be accessed by User : " + currentUserName);
            } else {
                return Boolean.TRUE;
            }

        }
        return Boolean.TRUE;
    }

    public String retrieveUsernameForCreatedBy(){
        String forReturn = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            forReturn = authentication.getName();

        }
        return forReturn;
    }

    public AccountDetails retrieveAccountDetails(User user){
        Optional<AccountDetails> accountDetails = accountDetailsRepository.findByUser(user);
        if(accountDetails.isPresent()){
            return accountDetails.get();
        } else {
            throw new EntityNotFoundException("User : "+ user.getUserName() + " has no accounts opened.");
        }


    }


    public Optional<User> retrieveUser(String username){
        return userRepository.findByUserName(username);
    }

}
