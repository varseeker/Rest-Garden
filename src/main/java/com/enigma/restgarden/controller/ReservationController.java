package com.enigma.restgarden.controller;


import com.enigma.restgarden.dto.ReservationDTO;
import com.enigma.restgarden.dto.ReservationUpdateDTO;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.service.reservation.ReservationServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {

    @Autowired
    ReservationServiceDbImpl reservationServiceDb;

    @GetMapping("/reservations")
    public List<Reservation> findAll(){
        return reservationServiceDb.getAllData();
    }

    @PostMapping("/reservation")
    public Reservation create(@RequestBody ReservationDTO reservationDto){
        return reservationServiceDb.createDataWithDto(reservationDto);
    }

    @PutMapping("/reservation/resubscribe")
    public Reservation updateWithDto(@RequestBody ReservationUpdateDTO reservationUpdateDto){
        return reservationServiceDb.updateDataWithDto(reservationUpdateDto);
    }

    @PutMapping("/reservation")
    public Reservation update(@RequestBody Reservation reservation){
        return reservationServiceDb.updateData(reservation);
    }

    @GetMapping("/reservation/{id}")
    public Reservation getByIdGrave(@PathVariable(name = "id") String id){
        return reservationServiceDb.getDataById(id);
    }

    @DeleteMapping("/reservation/{id}")
    public void deleteByIdGrave(@PathVariable(name = "id") String id){
        reservationServiceDb.deleteData(id);
    }
}
