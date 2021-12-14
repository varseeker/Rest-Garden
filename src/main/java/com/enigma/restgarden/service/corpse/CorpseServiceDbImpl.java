package com.enigma.restgarden.service.corpse;

import com.enigma.restgarden.dto.CorpseDTO;
import com.enigma.restgarden.dto.CorpseUpdateDTO;
import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.repo.CorpseRepository;
import com.enigma.restgarden.service.grave.GraveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CorpseServiceDbImpl implements CorpseService {

    @Autowired
    CorpseRepository corpseRepository;

    @Autowired
    GraveService graveService;

    @Override
    public Corpse getDataById(String id) {
        Optional<Corpse> corpseOptional = isCorpseExist(id);
        return corpseOptional.get();
    }

    private Optional<Corpse> isCorpseExist(String id) {
        Optional<Corpse> corpseOptional = corpseRepository.findById(id);
        if (!corpseOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find corpse with that id, please check and try again");
        }
        return corpseOptional;
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
        getDataById(corpse.getId());
        return corpseRepository.save(corpse);
    }

    @Override
    public CustomPage<Corpse> getAllDataWithPage(Pageable pageable, String clue) {
        Page<Corpse> pageData = corpseRepository.findAllByNameContains(clue, pageable);
        return new CustomPage<>(pageData);
    }

    public Corpse createDataWithDto(CorpseDTO corpseDto) {
        if (corpseDto.getName().isEmpty()){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cant let name is empty, please fill it and try again");
        }else {
            Grave grave = graveService.getDataById(corpseDto.getGraveId());
            Corpse corpse = new Corpse(corpseDto.getName(), corpseDto.getParentName(), corpseDto.getLocation(), grave, corpseDto.getBirthDate());
            corpseRepository.save(corpse);
            return corpse;
        }
    }

    public Corpse updateDataWithDto(CorpseUpdateDTO corpseUpdateDTO) {
        Corpse corpse = getDataById(corpseUpdateDTO.getId());
        Grave grave = graveService.getDataById(corpseUpdateDTO.getGraveId());
        corpse.setBirthDate(corpseUpdateDTO.getBirthDate());
        corpse.setLocation(corpseUpdateDTO.getLocation());
        corpse.setName(corpseUpdateDTO.getName());
        corpse.setParentName(corpseUpdateDTO.getParentName());
        corpse.setGrave(grave);
        return corpseRepository.save(corpse);
    }

}
