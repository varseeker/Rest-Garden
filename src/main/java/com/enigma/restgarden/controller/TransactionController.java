package com.enigma.restgarden.controller;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.dto.TransactionDTO;
import com.enigma.restgarden.entity.Transaction;
import com.enigma.restgarden.entity.Transaction;
import com.enigma.restgarden.service.transaction.TransactionServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionServiceDbImpl transactionServiceDb;

    @GetMapping("/transactions")
    public List<Transaction> findAll(){
        return transactionServiceDb.getAllData();
    }

    @PostMapping("/transaction")
    public Transaction create(@RequestBody TransactionDTO transactionDto){
        return transactionServiceDb.createDataWithDto(transactionDto);
    }

    @GetMapping("/transaction/user/{idUser}")
    public List<Transaction> getByIdUser(@PathVariable(name = "idUser") String id){
        return transactionServiceDb.getAllDataByUser(id);
    }

    @PutMapping("/transaction")
    public Transaction update(@RequestBody Transaction transaction){
        transactionServiceDb.updateData(transaction);
        return transaction;
    }

    @GetMapping("/transaction/pagination")
    public CustomPage<Transaction> findAllTransactionWithPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return transactionServiceDb.getAllDataWithPage(PageRequest.of(page, size));
    }
}
