package com.enigma.restgarden.controller;

import com.enigma.restgarden.entity.ManageImage;
import com.enigma.restgarden.service.manageimage.ManageImageServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ManageImageController {

    @Autowired
    ManageImageServiceDbImpl manageImageServiceDb;

    @GetMapping("/image/{id}")
    public void getImage(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ManageImage imageEntity = manageImageServiceDb.getBySourceId(id).get();
        response.setContentType(imageEntity.getType());
        response.getOutputStream().write(imageEntity.getImage());
        response.getOutputStream().close();
    }
}
