package org.project.library.Controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.project.library.Dto.UpdateUserEmailDTO;
import org.project.library.Dto.UpdateUserMobileDTO;
import org.project.library.Exceptions.DataNotFoundException;
import org.project.library.Model.Book;
import org.project.library.Model.User;
import org.project.library.Repository.UserRepository;
import org.project.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/v1/users" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService ;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {

        User user1 = userService.addNewUser(user) ;

        if(user1 != null)
            return new ResponseEntity<>(user1 , HttpStatus.CREATED) ;
        else
        {
            log.error("Cannot add the user");
            throw new RuntimeException("Cannot add the user");
        }

    }

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers()
    {
        List<User> users = userService.fetchAllUsers() ;

        if(!users.isEmpty())
            return new ResponseEntity<>(users , HttpStatus.FOUND) ;
        else
        {
            log.error("Cannot fetch the users");
            throw new RuntimeException("Cannot fetch the users");
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<User>> fetchALlBooksIssuedToUser(@PathVariable("userName") String userName)
    {
        User user = userRepository.findByName(userName) ;
        if(user != null)
        {
            List<User> list = userService.fetchAllBooksIssuedToUser(userName) ;
            return new ResponseEntity<>(list , HttpStatus.FOUND) ;
        }
        else
        {
            log.error(String.format("No user with user name : %s exists", userName));
            throw new DataNotFoundException(String.format("No user with user name : %s exists", userName));
        }
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<String> deleteUserByName(@PathVariable("userName") String userName)
    {
        User user = userRepository.findByName(userName) ;
        if(user != null)
        {
            String check = userService.deleteUserByName(userName) ;
            if(check != null)
                return new ResponseEntity<>(String.format("User with name : %s has been deleted", userName), HttpStatus.OK) ;
            else
                return new ResponseEntity<>("Not able to delete user information", HttpStatus.BAD_REQUEST) ;
        }
        else
        {
            log.error(String.format("No user with user name : %s, exists", userName));
            throw new DataNotFoundException(String.format("No user with user name : %s, exists", userName));
        }
    }

    @PutMapping("/mob")
    public ResponseEntity<String> updateUserNumber(@RequestBody @Valid UpdateUserMobileDTO object)
    {
        return new ResponseEntity<>(userService.updateUserMobile(object) , HttpStatus.OK) ;
    }

    @PutMapping("/mail")
    public ResponseEntity<String> updateUserEmail(@RequestBody @Valid UpdateUserEmailDTO object)
    {
        return new ResponseEntity<>(userService.updateUserEmail(object) , HttpStatus.OK) ;
    }
}
