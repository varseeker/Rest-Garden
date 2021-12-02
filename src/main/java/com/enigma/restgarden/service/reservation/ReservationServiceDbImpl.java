//package com.enigma.restgarden.service.reservation;
//
//import com.enigma.restgarden.entity.Reservation;
//import com.enigma.restgarden.repo.ReservationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ReservationServiceDbImpl implements ReservationService{
//
//    @Autowired
//    ReservationRepository reservationRepository;
//
//    @Override
//    public Reservation getDataById(String id) {
//        return reservationRepository.getById(id);
//    }
//
//    @Override
//    public List<Reservation> getAllData() {
//        return reservationRepository.findAll();
//    }
//
//    @Override
//    public Reservation createData(Reservation reservation) {
//        return null;
//    }
//
//    @Override
//    public void deleteData(String id) {
//        reservationRepository.deleteById(id);
//    }
//
//    @Override
//    public Reservation updateData(Reservation reservation) {
//        return null;
//    }
//}
