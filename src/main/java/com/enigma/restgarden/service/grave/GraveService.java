package com.enigma.restgarden.service.grave;

import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.service.CRUDService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface GraveService extends CRUDService<Grave> {
    List<Grave> searchByNameAndAddress(String graveName);
    Grave createWithFile(Grave grave, MultipartFile multipartFile) throws IOException;
    Grave updateWithFile(Grave grave, MultipartFile multipartFile) throws IOException;
}