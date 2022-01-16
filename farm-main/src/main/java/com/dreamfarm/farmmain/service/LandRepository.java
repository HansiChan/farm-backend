package com.dreamfarm.farmmain.service;

import com.dreamfarm.farmmain.entity.Land;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LandRepository extends JpaRepository<Land, Integer> {


}
