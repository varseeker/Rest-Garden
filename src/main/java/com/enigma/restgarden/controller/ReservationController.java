package com.enigma.restgarden.controller;


import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.dto.ReservationDTO;
import com.enigma.restgarden.dto.ReservationUpdateDTO;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.service.reservation.ReservationServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    @GetMapping("/reservations/reserve")
    public List<Reservation> findAllReserve(){
        return reservationServiceDb.getAllDataReserve();
    }

    @PostMapping("/reservation")
    public Reservation create(@RequestBody ReservationDTO reservationDto){
        return reservationServiceDb.createDataWithDto(reservationDto);
    }

    @PutMapping("/reservation/resubscribe")
    public Reservation updateWithDto(@RequestBody ReservationUpdateDTO reservationUpdateDto){
        return reservationServiceDb.updateDataWithDto(reservationUpdateDto);
    }

    @PutMapping("/reservation/assign")
    public Reservation checkIn(@RequestBody ReservationUpdateDTO reservationUpdateDto){
        return reservationServiceDb.checkIn(reservationUpdateDto);
    }

    @PutMapping("/reservation")
    public Reservation update(@RequestBody Reservation reservation){
        return reservationServiceDb.updateData(reservation);
    }

    @GetMapping("/reservation/{id}")
    public Reservation getById(@PathVariable(name = "id") String id){
        return reservationServiceDb.getDataById(id);
    }

    @GetMapping("/reservation/user/{idUser}")
    public List<Reservation> getByIdUser(@PathVariable(name = "idUser") String id){
        return reservationServiceDb.getAllReservationByUser(id);
    }

    @DeleteMapping("/reservation/{id}")
    public void deleteByIdGrave(@PathVariable(name = "id") String id){
        reservationServiceDb.deleteData(id);
    }

    @GetMapping("/reservation/pagination")
    public CustomPage<Reservation> findAllReservationWithPage(@RequestParam(name = "page") Integer page, @RequestParam(name = "size") Integer size){
        return reservationServiceDb.getAllDataWithPage(PageRequest.of(page, size));
    }
}
