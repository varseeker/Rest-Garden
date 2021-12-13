package com.enigma.restgarden.controller;

import com.enigma.restgarden.dto.CorpseDTO;
import com.enigma.restgarden.dto.CorpseUpdateDTO;
import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.service.corpse.CorpseService;
import com.enigma.restgarden.service.corpse.CorpseServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CorpseController {

    @Autowired
    CorpseServiceDbImpl corpseServiceDb;

    @GetMapping("/corpses")
    public List<Corpse> getAllCorpse() {
        return corpseServiceDb.getAllData();
    }

    @GetMapping("/corpse/{id}")
    public Corpse getCorpse(@PathVariable(name = "id") String id) {
        return corpseServiceDb.getDataById(id);
    }

    @DeleteMapping("/corpse/{id}")
    public void deleteCorpse(@PathVariable(name = "id") String id) {
        corpseServiceDb.deleteData(id);
    }

    @PostMapping("/corpse")
    public Corpse addCorpse(@RequestBody CorpseDTO corpseDto) {
        return corpseServiceDb.createDataWithDto(corpseDto);
    }

    @PutMapping("/corpse")
    public Corpse updateCorpse(@RequestBody CorpseUpdateDTO corpseUpdateDto) {
        return corpseServiceDb.updateDataWithDto(corpseUpdateDto);
    }

    @GetMapping("/corpse/pagination")
    public CustomPage<Corpse> findAllCorpseWithPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestParam(name = "clue") String clue) {
        return corpseServiceDb.getAllDataWithPage(PageRequest.of(page, size), clue);
    }
}
