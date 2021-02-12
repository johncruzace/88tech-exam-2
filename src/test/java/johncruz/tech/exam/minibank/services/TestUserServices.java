package johncruz.tech.exam.minibank.services;

import johncruz.tech.exam.minibank.model.domain.User;
import johncruz.tech.exam.minibank.model.response.ResponseObject;
import johncruz.tech.exam.minibank.service.UserService;
import johncruz.tech.exam.minibank.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class TestUserServices {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test_user_create(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setUsername("test");

        UserServiceImpl userService = mock(UserServiceImpl.class);

        User user = new User();
        user.setUserName("test");

        when(userService.createNewUser(user)).thenReturn(responseObject);

        assertEquals(responseObject.getUsername(),userService.createNewUser(user).getUsername());


    }

    @Test
    public void test_user_get_single(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setUsername("test");

        UserServiceImpl userService = mock(UserServiceImpl.class);

        User user = new User();
        user.setUserName("test");

        when(userService.retrieveSingleUser("user")).thenReturn(user);

        assertEquals("test",userService.retrieveSingleUser("user").getUserName());

    }

    @Test
    public void test_user_delete(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setUsername("test");

        UserServiceImpl userService = mock(UserServiceImpl.class);

        User user = new User();
        user.setUserName("test");

        when(userService.deleteUser("user")).thenReturn(responseObject);

        assertEquals("test",userService.deleteUser("user").getUsername());

    }

    @Test
    public void test_retrieve_all(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setUsername("test");

        UserServiceImpl userService = mock(UserServiceImpl.class);

        User user = new User();
        user.setUserName("test");
        List<User> userList = new ArrayList<User>();
        userList.add(user);

        when(userService.retrieveAllUsers()).thenReturn(userList);

        assertEquals(1,userService.retrieveAllUsers().size());

    }

    @Test
    public void test_retrieve_all_details(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setUsername("test");

        UserServiceImpl userService = mock(UserServiceImpl.class);

        User user = new User();
        user.setUserName("test");
        List<User> userList = new ArrayList<User>();
        userList.add(user);

        when(userService.retrieveAllUsers()).thenReturn(userList);

        assertEquals("test",userService.retrieveAllUsers().get(0).getUserName());

    }


}
