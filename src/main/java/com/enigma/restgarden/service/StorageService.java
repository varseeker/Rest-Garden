package com.enigma.restgarden.service;

import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class StorageService {


    public void saveFileTo(MultipartFile multipartFile, String id) throws IOException {

        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
//        File makeDir = new File("/home/dell/Academy/Pascal-7/RestGarden/upload-img/"+id);
//        makeDir.mkdir();
        String destination = "../../../upload-img/"+id+"."+extension;

        File fileTemp = new File(destination);
        multipartFile.transferTo(fileTemp);
    }
}
