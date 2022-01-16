package com.dreamfarm.farmmain.service;

import com.dreamfarm.farmmain.entity.PondTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PondTransactionRepository extends JpaRepository<PondTransaction, Integer> {
}
