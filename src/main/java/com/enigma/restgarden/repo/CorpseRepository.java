package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.Corpse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorpseRepository extends JpaRepository<Corpse, String> {
    public Page<Corpse> findAllByNameContains(String name, Pageable pageable);
}