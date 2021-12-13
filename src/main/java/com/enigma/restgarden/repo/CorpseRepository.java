package com.enigma.restgarden.repo;

import com.enigma.restgarden.entity.Corpse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CorpseRepository extends JpaRepository<Corpse, String> {
}