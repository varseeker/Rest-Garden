package com.enigma.restgarden.controller;

import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.service.StorageService;
import com.enigma.restgarden.service.grave.GraveService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/upload/{id}")
    public void uploadProfilePicture(@RequestPart(name = "file") MultipartFile multipartFile, @PathVariable(name = "id") String id) throws IOException {
        storageService.saveFileTo(multipartFile, id);
    }
}
