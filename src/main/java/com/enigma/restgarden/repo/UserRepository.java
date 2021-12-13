package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findUsersByUsername(String userName);
    public Optional<User> findUserByPassword(String password);
    public Optional<User> findUsersByUsernameAndPassword(String username, String password);
    public boolean existsByUsername(String userName);
    public boolean existsByEmail(String email);
}