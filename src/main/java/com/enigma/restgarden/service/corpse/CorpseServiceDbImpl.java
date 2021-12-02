package com.enigma.restgarden.service.corpse;

import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.repo.CorpseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpseServiceDbImpl implements CorpseService{

    @Autowired
    CorpseRepository corpseRepository;

    @Override
    public Corpse getDataById(String id) {
        return corpseRepository.getById(id);
    }

    @Override
    public List<Corpse> getAllData() {
        return corpseRepository.findAll();
    }

    @Override
    public Corpse createData(Corpse corpse) {
        return null;
    }

    @Override
    public void deleteData(String id) {
        corpseRepository.deleteById(id);
    }

    @Override
    public Corpse updateData(Corpse corpse) {
        return null;
    }
}
