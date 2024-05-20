package org.project.library.Service;

import org.project.library.Dto.UpdateUserEmailDTO;
import org.project.library.Dto.UpdateUserMobileDTO;
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

    public List<User> fetchAllBooksIssuedToUser(String userName)
    {
        User user = userRepository.findByName(userName) ;
        Integer id = user.getUserId() ;

        return userRepository.findById(user.getUserId()).stream().toList();
    }

    public String deleteUserByName(String userName)
    {
        User user = userRepository.findByName(userName) ;

        if(user != null)
        {
            userRepository.deleteById(user.getUserId());
            return "User deleted" ;
        }
        else
            return "User Not Found" ;
    }

    public String updateUserMobile(UpdateUserMobileDTO obj)
    {
        try {
            Integer id = obj.getUserId();
            User user = userRepository.findById(id).get();
            String newMob = obj.getNewMobileNumber();
            user.setUserMobile(newMob);
            userRepository.save(user);
            return String.format("Mobile number for user, name : %s , updated successfully",user.getUserName());
        }
        catch(Exception e)
        {
            return String.format("Not able to update mobile number for user, due to : " + e.getMessage());
        }
    }

    public String updateUserEmail(UpdateUserEmailDTO obj)
    {
        try {
            Integer id = obj.getUserId();
            User user = userRepository.findById(id).get();
            String newEmail = obj.getNewEmail();
            user.setUserEmail(newEmail);
            userRepository.save(user);
            return String.format("Mail for user, name : %s , updated successfully",user.getUserName());
        }
        catch(Exception e)
        {
            return String.format("Not able to update mail for user, due to : " + e.getMessage());
        }
    }
}

