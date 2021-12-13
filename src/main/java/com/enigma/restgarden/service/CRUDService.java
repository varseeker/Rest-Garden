package com.enigma.restgarden.service;

import com.enigma.restgarden.dto.CustomPage;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface CRUDService<T> {
    public T getDataById(String id);
    public List<T> getAllData();
    public T createData(T t);
    public void deleteData(String id);
    public T updateData(T t);
    public CustomPage<T> getAllDataWithPage(Pageable pageable, String clue);
}
