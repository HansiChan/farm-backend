package com.dreamfarm.farmmain.service;

import com.dreamfarm.farmmain.entity.LandTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandTransactionRepository extends JpaRepository<LandTransaction, Integer> {
}
