package com.enigma.restgarden.service.grave;

import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.service.CRUDService;

import java.util.List;

public interface GraveService extends CRUDService<Grave> {
    List<Grave> searchByNameAndAddress(String graveName);
}