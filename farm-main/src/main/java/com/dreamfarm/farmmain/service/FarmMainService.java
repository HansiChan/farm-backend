package com.dreamfarm.farmmain.service;

import com.dreamfarm.farmmain.entity.Land;
import com.dreamfarm.farmmain.entity.LandTransaction;
import com.dreamfarm.farmmain.entity.Pond;
import com.dreamfarm.farmmain.entity.PondTransaction;

import java.util.List;

public interface FarmMainService {

    void saveLandTransaction(LandTransaction landTransaction) throws Exception;

    void savePondTransaction(PondTransaction pondTransaction) throws Exception;

    List<LandTransaction> getLandTransaction();

    List<PondTransaction> getPondTransaction();

    void mintLand();

    void mintPond();

    Land getLandById(Integer tokenId) throws Exception;

    Pond getPondById(Integer tokenId) throws Exception;

}
