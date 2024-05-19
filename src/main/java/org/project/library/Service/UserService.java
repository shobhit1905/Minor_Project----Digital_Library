package org.project.library.Service;

import org.project.library.Model.Book;
import org.project.library.Model.User;
import org.project.library.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository ;


    public User addNewUser(User user) {

        return userRepository.save(user) ;
    }


    public List<User> fetchAllUsers()
    {
        return userRepository.findAll() ;
    }

    public List<Book> fetchAllBooksIssuesToUser(String userName)
    {
        User user = userRepository.findByName(userName) ;
        Integer id = user.getUserId() ;

        return userRepository.findAllBooksIssuedToUser(id) ;

    }
}
