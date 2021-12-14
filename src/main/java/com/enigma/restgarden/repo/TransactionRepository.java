package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.Transaction;
import com.enigma.restgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByUserAndStatus(User user, String status);
}