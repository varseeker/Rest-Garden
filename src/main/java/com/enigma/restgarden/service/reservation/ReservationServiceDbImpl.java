package com.enigma.restgarden.service.reservation;

import com.enigma.restgarden.dto.CustomPage;
import com.enigma.restgarden.dto.ReservationDTO;
import com.enigma.restgarden.dto.ReservationUpdateDTO;
import com.enigma.restgarden.dto.TransactionDTO;
import com.enigma.restgarden.entity.*;
import com.enigma.restgarden.repo.ReservationRepository;
import com.enigma.restgarden.service.grave.GraveServiceDbImpl;
import com.enigma.restgarden.service.transaction.TransactionServiceDbImpl;
import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceDbImpl implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserServiceDbImpl userServiceDb;

    @Autowired
    GraveServiceDbImpl graveServiceDb;

    @Autowired
    TransactionServiceDbImpl transactionServiceDb;


    @Override
    public Reservation getDataById(String id) {
        Optional<Reservation> reservationOptional = isReservationExist(id);
        return reservationOptional.get();
    }

    private Optional<Reservation> isReservationExist(String id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (!reservationOptional.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant find reservation with that id, please check and try again");
        }
        return reservationOptional;
    }

    @Override
    public List<Reservation> getAllData() {
        List<Reservation> dataReservation = reservationRepository.findAll();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (Reservation data: dataReservation) {
            if (timestamp.after(data.getExpiredDate())){
                data.getGrave().setAvailableSlots(data.getGrave().getAvailableSlots() + data.getTotalSlot());
                Transaction transaction = new Transaction(data.getUser(), data.getGrave(), data.getTotalSlot(), data.getDescription());
                transaction.setType("Reservation");
                transaction.setStatus("EXPIRED");
                transactionServiceDb.createData(transaction);
                deleteDataJustById(data.getId());
            }
        }
        return reservationRepository.findAll();
    }
    public List<Reservation> getAllDataReserve() {
        return reservationRepository.findAllByStatus("Reserved");
    }

    @Override
    public Reservation createData(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteData(String id) {
        Reservation reservation = getDataById(id);
        Transaction transaction = new Transaction(reservation.getUser(), reservation.getGrave(), reservation.getTotalSlot(), reservation.getDescription());
        transaction.setType("Reservation");
        transaction.setStatus("CANCELED");
        transactionServiceDb.createData(transaction);
        reservation.getGrave().setAvailableSlots(reservation.getGrave().getAvailableSlots() + reservation.getTotalSlot());
        reservationRepository.deleteById(reservation.getId());
    }

    public void deleteDataJustById(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateData(Reservation reservation) {
        getDataById(reservation.getId());
        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public CustomPage<Reservation> getAllDataWithPage(Pageable pageable, String clue) {
        Page<Reservation> pageData = reservationRepository.findAll(pageable);
        return new CustomPage<>(pageData);
    }

    public Reservation updateDataWithDto(ReservationUpdateDTO reservationUpdateDTO) {
        Reservation reservation = getDataById(reservationUpdateDTO.getReservationId());
        Timestamp timestamp = new Timestamp(reservation.getExpiredDate().getTime()+((1000*(60*60))*24));
        reservation.setExpiredDate(timestamp);
        Double fee = reservation.getGrave().getPrice() * 0.2;
        reservation.setTotalPayment(reservation.getTotalPayment()+(fee.intValue()*reservation.getTotalSlot()));
        return reservationRepository.save(reservation);
    }

    public Reservation checkIn(ReservationUpdateDTO reservationUpdateDTO) {
        Reservation reservation = getDataById(reservationUpdateDTO.getReservationId());
        reservation.setStatus("Assign");
        Transaction transaction = new Transaction(reservation.getUser(), reservation.getGrave(), reservation.getTotalSlot(), reservation.getDescription());
        transaction.setTotalPrice(reservation.getTotalPayment());
        transaction.setType("Reservation");
        transaction.setStatus("SUCCESS");
        transactionServiceDb.createData(transaction);
        reservationRepository.deleteById(reservation.getId());
        return reservation;
    }

    public Reservation createDataWithDto(ReservationDTO reservationDto) {
        Grave grave = graveServiceDb.getDataById(reservationDto.getGraveId());
        if (grave.getType().equals("Public")){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Grave type is public, you cant bought or book Public Cemeteries, please check and try again");
        }
        User user = userServiceDb.getDataById(reservationDto.getUserId());
        Integer slot = grave.getAvailableSlots() - reservationDto.getTotalSlot();
        if (slot < 0){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "slot is unavailable or is full, please check and try again");
        }else {
            grave.setAvailableSlots(slot);
            Reservation reservation = new Reservation(grave, user, reservationDto.getTotalSlot(), reservationDto.getStatus(), reservationDto.getDescription() );
            reservation.setGravePrice(grave.getPrice());
            Double bookingFee = reservation.getGravePrice() * 0.2;
            reservation.setTotalPayment((reservation.getGravePrice()+bookingFee.intValue())*reservation.getTotalSlot());

            reservation.setStatus("Reserved");

            return reservationRepository.save(reservation);
        }
    }

    public List<Reservation> getAllReservationByUser(String id){
        User user = userServiceDb.getDataById(id);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Reservation> listByUser = reservationRepository.findAllByUserAndStatus(user, "Reserved");
        for (Reservation data: listByUser) {
            if (timestamp.after(data.getExpiredDate())){
                data.getGrave().setAvailableSlots(data.getGrave().getAvailableSlots() + data.getTotalSlot());
                deleteDataJustById(data.getId());
            }
        }
        return reservationRepository.findAllByUserAndStatus(user, "Reserved");
    }
}
