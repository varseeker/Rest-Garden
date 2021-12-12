package com.enigma.restgarden.service.grave;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.entity.Corpse;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.entity.ManageImage;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.repo.GraveRepository;
import com.enigma.restgarden.service.manageimage.ManageImageServiceDbImpl;
import com.enigma.restgarden.service.reservation.ReservationServiceDbImpl;
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
public class GraveServiceDbImpl implements GraveService{

    @Autowired
    GraveRepository graveRepository;

    @Autowired
    ManageImageServiceDbImpl manageImageServiceDb;

    @Override
    public Grave getDataById(String id) {
        Optional<Grave> graveOptional = isReservationExist(id);
        return graveOptional.get();
    }

    private Optional<Grave> isReservationExist(String id) {
        Optional<Grave> graveOptional = graveRepository.findById(id);
        if (!graveOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find reservation with that id, please check and try again");
        }
        return graveOptional;
    }

    @Override
    public List<Grave> getAllData() {
        return graveRepository.findAll();
    }

    @Override
    public Grave createData(Grave grave) {
        if (grave.getPrice() < 0 || grave.getAvailableSlots() < 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Price or Available Slots cant lower than 0, please check and try again");
        }else {
            return graveRepository.save(grave);
        }
    }

    @Override
    public void deleteData(String id) {
        graveRepository.deleteById(id);
    }

    @Override
    public Grave updateData(Grave grave) {
        getDataById(grave.getId());
        graveRepository.save(grave);
        return grave;
    }

    @Override
    public CustomPage<Grave> getAllDataWithPage(Pageable pageable) {
        Page<Grave> pageData = graveRepository.findAll(pageable);
        return new CustomPage<>(pageData);
    }

    @Override
    public List<Grave> searchByNameAndAddress(String graveName){
        return graveRepository.findAllByAddressContainsOrNameContainsIgnoreCase(graveName, graveName);
    }

    @Override
    public Grave createWithFile(Grave grave, MultipartFile multipartFile) throws IOException {
        grave = createData(grave);
        buildGrave(grave, multipartFile);
        return updateData(grave);
    }

    @Override
    public Grave updateWithFile(Grave grave, MultipartFile multipartFile) throws IOException {
        grave = updateData(grave);
        buildGrave(grave, multipartFile);
        System.out.println(grave);
        return updateData(grave);
    }

    private void buildGrave(Grave grave, MultipartFile multipartFile) throws IOException {
        if(multipartFile != null){
            setGraveWithFile(grave, multipartFile);
        } else {
            setGraveWithoutFile(grave);
        }
    }

    private void setGraveWithFile(Grave grave, MultipartFile multipartFile) throws IOException {
        if(!multipartFile.isEmpty()){
            manageImageServiceDb.addMultipartFile(grave.getId(), multipartFile);
            grave.setImage("https://rest-garden.herokuapp.com/api/image/" + grave.getId());
        }
    }

    private void setGraveWithoutFile(Grave grave) {
        Optional<ManageImage> imageEntity = manageImageServiceDb.getBySourceId(grave.getId());
        imageEntity.ifPresent(entity -> manageImageServiceDb.deleteData(entity.getId()));
        grave.setImage(null);
    }
}
