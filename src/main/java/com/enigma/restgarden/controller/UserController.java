package com.enigma.restgarden.controller;

import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.service.user.UserService;
import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserServiceDbImpl userServiceDb;

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.getAllData();
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        return userService.createData(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable(name = "id") String id){
        userService.deleteData(id);
    }

//    @PutMapping("/user")
//    public User updateUser(@RequestBody User user){
//        userService.updateData(user);
//        return user;
//    }

    @PutMapping("/user")
    public User updateUserCoba(@RequestBody User user){
        userService.updateData(user);
        return user;
    }

}
