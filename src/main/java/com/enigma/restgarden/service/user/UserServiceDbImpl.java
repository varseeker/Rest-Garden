package com.enigma.restgarden.service.user;

import com.enigma.restgarden.dto.UserCredentials;
import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.repo.UserRepository;
import com.enigma.restgarden.security.JwtTokenUtil;
import com.enigma.restgarden.service.UserDetailServiceDbImpl;
import com.enigma.restgarden.service.grave.GraveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceDbImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    GraveService graveService;

    @Autowired
    UserDetailServiceDbImpl userDetailServiceDb;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        if (user.getUsername().isEmpty() || user.getName().isEmpty() || user.getPhoneNumber().isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Please fill the form to register, please try again");
        }else {
            if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User with that name or email has exist, please check and try again");
            }else {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                return userRepository.save(user);
            }
        }
    }

    @Override
    public void deleteData(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateData(User user) {
        getDataById(user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public Map<String, Object> signIn(UserCredentials userCredentials){
        if (userCredentials.getUsername().isEmpty() || userCredentials.getPassword().isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Username and Password must fill, please check and try again");
        }else {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword());
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            UserDetails userDetails = userDetailServiceDb.loadUserByUsername(userCredentials.getUsername());

            String token = jwtTokenUtil.generateToken(userDetails);

            User user = userRepository.findUsersByUsername(userCredentials.getUsername()).get();
            Map<String, Object> tokenWrapper = new HashMap<>();
            tokenWrapper.put("id", user.getId());
            tokenWrapper.put("token", token);

            return tokenWrapper;
        }
    }
}
