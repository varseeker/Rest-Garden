package com.enigma.restgarden.service.transaction;

import com.enigma.restgarden.dto.TransactionDTO;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.entity.Transaction;
import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.repo.TransactionRepository;
import com.enigma.restgarden.service.grave.GraveServiceDbImpl;
import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceDbImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    GraveServiceDbImpl graveServiceDb;

    @Autowired
    UserServiceDbImpl userServiceDb;

    @Override
    public Transaction getDataById(String id) {
        Optional<Transaction> customerOptional = isTransactionExist(id);
        return customerOptional.get();
    }

    private Optional<Transaction> isTransactionExist(String id) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (!transactionOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found for this transaction id, please check and try again");
        }
        return transactionOptional;
    }

    @Override
    public List<Transaction> getAllData() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction createData(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteData(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction updateData(Transaction transaction) {
        return null;
    }

    public Transaction createDataWithDto(TransactionDTO transactionDto) {
        Grave grave = graveServiceDb.getDataById(transactionDto.getGraveId());
        if (grave.getType().equals("Public")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Grave type is public, you cant bought or book Public Cemeteries, please check and try again");
        }
        User user = userServiceDb.getDataById(transactionDto.getUserId());
        Integer slot = grave.getAvailableSlots() - transactionDto.getTotalSlot();
        if (slot < 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "slot is unavailable or is full, please check and try again");
        }else {
            Integer balance = transactionDto.getBalance() ;
            grave.setAvailableSlots(slot);
            Transaction transaction = new Transaction(user, grave, transactionDto.getTotalSlot(), transactionDto.getBalance(), transactionDto.getCurrentDate(), transactionDto.getDescription());
            return transactionRepository.save(transaction);
        }
    }
}
