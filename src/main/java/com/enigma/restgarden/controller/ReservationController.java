package com.enigma.restgarden.controller;


import com.enigma.restgarden.dto.ReservationDTO;
import com.enigma.restgarden.dto.ReservationUpdateDTO;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.service.reservation.ReservationServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

    @PutMapping("/reservation")
    public Reservation update(@RequestBody ReservationUpdateDTO reservationUpdateDto){
        return reservationServiceDb.updateDataWithDto(reservationUpdateDto);
    }

}
