package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.Reservation;
import com.enigma.restgarden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    public List<Reservation> findAllByUser(User user);
}