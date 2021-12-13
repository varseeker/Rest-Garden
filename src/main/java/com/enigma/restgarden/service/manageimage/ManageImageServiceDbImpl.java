package com.enigma.restgarden.service.manageimage;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.entity.ManageImage;
import com.enigma.restgarden.repo.ManageImageRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ManageImageServiceDbImpl implements ManageImageService {

    @Autowired
    ManageImageRepository manageImageRepository;

    @Override
    public ManageImage getDataById(String id) {
        Optional<ManageImage> manageImageOptional = isManageImageExist(id);
        return manageImageOptional.get();
    }

    public Optional<ManageImage> getBySourceId(String id){
        return manageImageRepository.findBySourceId(id);
    }

    private Optional<ManageImage> isManageImageExist(String id) {
        Optional<ManageImage> manageImageOptional = manageImageRepository.findById(id);
        if (!manageImageOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find manageImage with that id, please check and try again");
        }
        return manageImageOptional;
    }

    @Override
    public List<ManageImage> getAllData() {
        return manageImageRepository.findAll();
    }

    @Override
    public ManageImage createData(ManageImage manageImage) {
        return manageImageRepository.save(manageImage);
    }

    @Override
    public void deleteData(String id) {
        getDataById(id);
        manageImageRepository.deleteById(id);
    }

    @Override
    public ManageImage updateData(ManageImage manageImage) {
        getDataById(manageImage.getId());
        return manageImageRepository.save(manageImage);
    }

    @Override
    public CustomPage<ManageImage> getAllDataWithPage(Pageable pageable, String clue) {
        Page<ManageImage> pageData = manageImageRepository.findAll(pageable);
        return new CustomPage<>(pageData);
    }

    public void addMultipartFile(String id, MultipartFile multipartFile) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        validateFileExtension(FilenameUtils.getExtension(originalName));
        ManageImage manageImage;
        manageImage = getManageImage(id);
        manageImage.setSourceId(id);
        manageImage.setImage(multipartFile.getBytes());
        manageImage.setType("image/" + FilenameUtils.getExtension(originalName));
        addManageImageToDatabase(manageImage);
    }

    public void validateFileExtension(String ext){
        ext = ext.toUpperCase();
        if (!((ext.equals("JPG")) || (ext.equals("PNG")) || (ext.equals("JPEG")))){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cant set image with that extension, please check and try again");
        }
    }

    private void addManageImageToDatabase(ManageImage manageImage) {
        if(manageImage.getId() != null){
            updateData(manageImage);
        } else {
            createData(manageImage);
        }
    }

    private ManageImage getManageImage(String id) {
        ManageImage manageImage;
        if(manageImageRepository.findBySourceId(id).isPresent()){
            manageImage = manageImageRepository.findBySourceId(id).get();
        } else {
            manageImage = new ManageImage();
        }
        return manageImage;
    }
}
