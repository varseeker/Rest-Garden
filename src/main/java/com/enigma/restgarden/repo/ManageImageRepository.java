package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.ManageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManageImageRepository extends JpaRepository<ManageImage, String> {
    Optional<ManageImage> findBySourceId(String id);
}
