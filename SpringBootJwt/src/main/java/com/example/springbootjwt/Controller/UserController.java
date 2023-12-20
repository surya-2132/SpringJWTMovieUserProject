package com.example.springbootjwt.Controller;

import com.example.springbootjwt.Exception.IdException;
import com.example.springbootjwt.Model.User;
import com.example.springbootjwt.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public String saveUser(User user){
        return userService.signUp(user);
    }

    @GetMapping("/getUser/{id}")
    public User getUserById(ObjectId id) throws IdException {
        return userService.getUserById(id);
    }

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() throws Exception {
        return userService.getAllUsers();
    }
}
