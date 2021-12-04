package com.enigma.restgarden.service;

import com.enigma.restgarden.dto.UserDetailImpl;
import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceDbImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if (!userRepository.findUsersByUsername(userName).isPresent()){
            throw new UsernameNotFoundException("KAGA ADA!!!");
        }
        //Ambil dari akun
        User account = userRepository.findUsersByUsername(userName).get();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (account.getUsername().equals("admin")){
            grantedAuthorities.add(new SimpleGrantedAuthority("Admin"));
        }
        grantedAuthorities.add(new SimpleGrantedAuthority("User"));

        UserDetails userDetails = new UserDetailImpl(account.getUsername(), account.getPassword(), grantedAuthorities);
        return userDetails;
    }
}
