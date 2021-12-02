package com.enigma.restgarden.service.grave;

import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.repo.GraveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraveServiceDbImpl implements GraveService{

    @Autowired
    GraveRepository graveRepository;

    @Override
    public Grave getDataById(String id) {
        return graveRepository.getById(id);
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
        return graveRepository.save(grave);
    }

    @Override
    public List<Grave> searchByNameAndAddress(String graveName){
        return graveRepository.findAllByAddressContainsOrNameContainsIgnoreCase(graveName, graveName);
    }
}
