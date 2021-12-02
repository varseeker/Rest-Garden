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
        Optional<User> customerOptional = isUserExist(id);
        return customerOptional.get();
    }

    private Optional<User> isUserExist(String id) {
        Optional<User> customerOptional = userRepository.findById(id);
        if (!customerOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Already Have Merchant with that name, please use another name");
        }
        return customerOptional;
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
    public User updateData(User customer) {
        getDataById(customer.getId());
        userRepository.save(customer);
        return customer;
    }
}
