package com.dreamfarm.farmmain.service;

import com.dreamfarm.farmmain.entity.Pond;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondRepository extends JpaRepository<Pond, Integer> {
}
