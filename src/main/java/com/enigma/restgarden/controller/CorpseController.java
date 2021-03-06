package com.enigma.restgarden.controller;

import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.service.corpse.CorpseService;
import com.enigma.restgarden.service.corpse.CorpseServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CorpseController {

    @Autowired
    CorpseServiceDbImpl corpseServiceDb;

    @GetMapping("/corpses")
    public List<Corpse> getAllCorpse(){
        return corpseServiceDb.getAllData();
    }

    @GetMapping("/corpse/{id}")
    public Corpse getCorpse(@PathVariable(name = "id") String id) {return corpseServiceDb.getDataById(id); }
}
