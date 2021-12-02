package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.Grave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GraveRepository extends JpaRepository<Grave, String>, JpaSpecificationExecutor<Grave> {
    List<Grave> findAllByAddressContainsOrNameContainsIgnoreCase(String address, String graveName);
}