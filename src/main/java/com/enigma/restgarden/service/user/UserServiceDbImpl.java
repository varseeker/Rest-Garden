package com.enigma.restgarden.service.user;

import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.repo.UserRepository;
import com.enigma.restgarden.service.grave.GraveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceDbImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    GraveService graveService;

    @Override
    public User getDataById(String id) {
        Optional<User> userOptional = isUserExist(id);
        return userOptional.get();
    }

    private Optional<User> isUserExist(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find user with that id, please check and try again");
        }
        return userOptional;
    }

    @Override
    public List<User> getAllData() {
        return userRepository.findAll();
    }

    @Override
    public User createData(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteData(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateData(User user) {
        getDataById(user.getId());
        userRepository.save(user);
        return user;
    }
}
