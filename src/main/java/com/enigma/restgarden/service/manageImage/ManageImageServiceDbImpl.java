package com.enigma.restgarden.service.manageImage;

import com.enigma.restgarden.entity.ManageImage;
import com.enigma.restgarden.repo.ManageImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    private Optional<ManageImage> isManageImageExist(String id) {
        Optional<ManageImage> manageImageOptional = manageImageRepository.findById(id);
        if (!manageImageOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find manageImage with that id, please check and try again");
        }
        return manageImageOptional;
    }

    @Override
    public List<ManageImage> getAllData() {
        return null;
    }

    @Override
    public ManageImage createData(ManageImage manageImage) {
        return null;
    }

    @Override
    public void deleteData(String id) {

    }

    @Override
    public ManageImage updateData(ManageImage manageImage) {
        return null;
    }
}
