package com.enigma.restgarden.controller;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.service.StorageService;
import com.enigma.restgarden.service.grave.GraveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GraveController {

    @Autowired
    GraveService graveService;

    @Autowired
    StorageService storageService;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping("/graves")
    public List<Grave> findAll(){
        return graveService.getAllData();
    }

    @PostMapping("/grave")
    public Grave create(@RequestBody Grave grave){
        return graveService.createData(grave);
    }

    @PutMapping("/grave")
    public Grave update(@RequestBody Grave grave){
        graveService.updateData(grave);
        return grave;
    }

    @GetMapping("/grave/{id}")
    public Grave getByIdGrave(@PathVariable(name = "id") String id){
        return graveService.getDataById(id);
    }

    @DeleteMapping("/grave/{id}")
    public void deleteGrave(@PathVariable(name = "id") String id){
        graveService.deleteData(id);
    }


    @GetMapping("/graves/{name}")
    public List<Grave> findByNameAndAdress(@PathVariable(name = "name") String name){
        return graveService.searchByNameAndAddress(name);
    }

    @PostMapping("/register/upload")
    public Grave createGraveWithImage(@RequestPart String graveString, @Nullable @RequestPart("image") MultipartFile multipartFile) throws IOException {
        Grave grave = objectMapper.readValue(graveString, Grave.class);
        return graveService.createWithFile(grave, multipartFile);
    }

    @PutMapping("/register/upload")
    public Grave updateGraveWithImage(@RequestPart String graveString, @Nullable @RequestPart("image") MultipartFile multipartFile) throws IOException {
        Grave grave = objectMapper.readValue(graveString, Grave.class);
        return graveService.updateWithFile(grave, multipartFile);
    }

    @GetMapping("/grave/pagination")
    public CustomPage<Grave> findAllGraveWithPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size, @RequestParam(name = "clue") String clue){
        return graveService.getAllDataWithPage(PageRequest.of(page, size), clue);
    }
}
