package com.example.springbootjwt.Service;

import com.example.springbootjwt.Exception.IdException;
import com.example.springbootjwt.Model.User;
import com.example.springbootjwt.Repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private TokenService tokenService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(User user){    //saveUser
        User saveUser = userRepository.save(user);
        return "{" +
                "\"message\":" + "Successfully created user\",\n"+
                "\"data\":" + saveUser +",\n"+
                "}";
    }

    public User getUserById(ObjectId id) throws IdException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new IdException("User Not Found");
        }
        return user.orElseGet(user::get);
    }

    public List<User> getAllUsers() throws Exception{
        return userRepository.findAll();
    }

    public String getUserByAgeLessThan(int age){
        List<User> optUser = userRepository.getUserLessThanAge(age);
        if(optUser.isEmpty()){
            return "User Not Found -> getUserByAgeLessThan";
        }else{
            return "{'Message': User found.\n\t 'data':" + optUser.toString()+"}";
        }
    }

    public String getUserByNameOrAge(String name, String age){
        List<User> user = userRepository.getUserByNameOrEmail(name, age);
        if(user.isEmpty()){
            return "User Not Found -> getUserByNameOrAge";
        }else{
            return "{'Message': UserFound.\n\t 'data'" + user.toString()+"}";
        }
    }

    public String logIn(String email, String password){
        List<User> users = userRepository.getUserByEmail(email);
        if(users.isEmpty()){
            return "Authentication Failed: User Not Found";
        }else if (!users.get(0).getPassword().equals(password)){    //If password didn't match
            return "Password Incorrect";
        }

        return "{\n"+
                "\"message\":" + "\" Successfully Logged-In\", \n"+
                "\"data\":" + "{\n" + "Name : " + users.get(0).getName()+", \n"+
                "Email: " + users.get(0).getEmail()+"\n"+
                "\"Token\": \"" + tokenService.createTokenAndEncrypt(users.get(0).getId())+"\""+
                "}";
    }
}
