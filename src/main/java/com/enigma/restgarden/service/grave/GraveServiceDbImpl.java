package com.enigma.restgarden.service.grave;

import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.repo.GraveRepository;
import com.enigma.restgarden.service.reservation.ReservationServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class GraveServiceDbImpl implements GraveService{

    @Autowired
    GraveRepository graveRepository;

    @Override
    public Grave getDataById(String id) {
        Optional<Grave> graveOptional = isReservationExist(id);
        return graveOptional.get();
    }

    private Optional<Grave> isReservationExist(String id) {
        Optional<Grave> graveOptional = graveRepository.findById(id);
        if (!graveOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find reservation with that id, please check and try again");
        }
        return graveOptional;
    }

    @Override
    public List<Grave> getAllData() {
        return graveRepository.findAll();
    }

    @Override
    public Grave createData(Grave grave) {
        return graveRepository.save(grave);
    }

    @Override
    public void deleteData(String id) {
        graveRepository.deleteById(id);
    }

    @Override
    public Grave updateData(Grave grave) {
        getDataById(grave.getId());
        graveRepository.save(grave);
        return grave;
    }

    @Override
    public List<Grave> searchByNameAndAddress(String graveName){
        return graveRepository.findAllByAddressContainsOrNameContainsIgnoreCase(graveName, graveName);
    }
}
