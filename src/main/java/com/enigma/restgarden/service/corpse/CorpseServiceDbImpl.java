package com.enigma.restgarden.service.corpse;

import com.enigma.restgarden.dto.CorpseDTO;
import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.repo.CorpseRepository;
import com.enigma.restgarden.service.grave.GraveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorpseServiceDbImpl implements CorpseService {

    @Autowired
    CorpseRepository corpseRepository;

    @Autowired
    GraveService graveService;

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
        return corpseRepository.save(corpse);
    }


    @Override
    public void deleteData(String id) {
        corpseRepository.deleteById(id);
    }

    @Override
    public Corpse updateData(Corpse corpse) {
        return null;
    }

    public Corpse createDataWithDto(CorpseDTO corpseDto) {
        Grave grave = graveService.getDataById(corpseDto.getGraveId());
        Corpse corpse = new Corpse(corpseDto.getName(), corpseDto.getParentName(), corpseDto.getLocation(), grave);
        corpseRepository.save(corpse);
        return corpse;
    }
}
