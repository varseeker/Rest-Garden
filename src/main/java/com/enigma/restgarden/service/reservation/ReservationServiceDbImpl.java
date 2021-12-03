package com.enigma.restgarden.service.reservation;

import com.enigma.restgarden.dto.ReservationDTO;
import com.enigma.restgarden.dto.ReservationUpdateDTO;
import com.enigma.restgarden.entity.Grave;
import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.entity.Transaction;
import com.enigma.restgarden.entity.User;
import com.enigma.restgarden.repo.ReservationRepository;
import com.enigma.restgarden.service.grave.GraveServiceDbImpl;
import com.enigma.restgarden.service.user.UserService;
import com.enigma.restgarden.service.user.UserServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ReservationServiceDbImpl implements ReservationService{

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    UserServiceDbImpl userServiceDb;

    @Autowired
    GraveServiceDbImpl graveServiceDb;

    @Override
    public Reservation getDataById(String id) {
        return reservationRepository.getById(id);
    }

    @Override
    public List<Reservation> getAllData() {
        List<Reservation> dataReservation = reservationRepository.findAll();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for (Reservation data: dataReservation) {
            if (timestamp.after(data.getExpiredDate())){
                data.getGrave().setAvailableSlots(data.getGrave().getAvailableSlots() + data.getTotalSlot());
                deleteData(data.getId());
            }else {
                return reservationRepository.findAll();
            }
        }
        return reservationRepository.findAll();
    }

    @Override
    public Reservation createData(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteData(String id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation updateData(Reservation reservation) {
        return null;
    }

    public Reservation updateDataWithDto(ReservationUpdateDTO reservationUpdateDTO) {
        Reservation reservation = getDataById(reservationUpdateDTO.getReservationId());
        Timestamp timestamp = new Timestamp(reservation.getExpiredDate().getTime()+(1000*60));
        reservation.setExpiredDate(timestamp);
        return reservationRepository.save(reservation);
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
            Integer balance = reservationDto.getUserBalance() ;
            grave.setAvailableSlots(slot);
            Reservation reservation = new Reservation(grave, user, reservationDto.getUserBalance(), reservationDto.getTotalSlot(), reservationDto.getStatus(), reservationDto.getDescription() );
            return reservationRepository.save(reservation);
        }
    }
}
