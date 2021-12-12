package com.enigma.restgarden.controller;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.dto.UserCredentials;
import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.service.user.UserService;
import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable(name = "id") String id){
        return userService.getDataById(id);
    }

    @PostMapping("/register")
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

    @PostMapping("/signin")
    public Map<String, Object> signInAccount(@RequestBody UserCredentials userCredentials){
        return userServiceDb.signIn(userCredentials);
    }

    @GetMapping("/user/pagination")
    public CustomPage<User> findAllUserWithPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return userServiceDb.getAllDataWithPage(PageRequest.of(page, size));
    }
}
