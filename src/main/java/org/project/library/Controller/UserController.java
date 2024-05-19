package org.project.library.Controller;

import org.apache.coyote.Response;
import org.project.library.Model.Book;
import org.project.library.Model.User;
import org.project.library.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/users" , produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService ;

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.addNewUser(user) , HttpStatus.CREATED) ;
    }

    @GetMapping
    public ResponseEntity<List<User>> fetchAllUsers()
    {
        return new ResponseEntity<>(userService.fetchAllUsers() , HttpStatus.FOUND) ;
    }

    @GetMapping("/{userName}")
    public ResponseEntity<List<Book>> fetchALlBooksIssuedToUser(@PathVariable("userName") String userName)
    {
        return new ResponseEntity<>(userService.fetchAllBooksIssuesToUser(userName) , HttpStatus.FOUND) ;
    }
}
