package com.dreamfarm.farmmain.service.impl;

import com.dreamfarm.farmmain.entity.Land;
import com.dreamfarm.farmmain.entity.LandTransaction;
import com.dreamfarm.farmmain.entity.Pond;
import com.dreamfarm.farmmain.entity.PondTransaction;
import com.dreamfarm.farmmain.service.FarmMainService;
import com.dreamfarm.farmmain.service.LandRepository;
import com.dreamfarm.farmmain.service.PondRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class FarmMainImpl implements FarmMainService {

    private final LandTransactionImpl landTransactionImpl;
    private final PondTransactionImpl pondTransactionImpl;
    private final LandRepository landRepository;
    private final PondRepository pondRepository;

    @Autowired
    public FarmMainImpl(LandTransactionImpl landTransactionImpl, PondTransactionImpl pondTransactionImpl,
                        LandRepository landRepository, PondRepository pondRepository) {
        this.landTransactionImpl = landTransactionImpl;
        this.pondTransactionImpl = pondTransactionImpl;
        this.landRepository = landRepository;
        this.pondRepository = pondRepository;
    }

    @Override
    public void saveLandTransaction(LandTransaction landTransaction) throws Exception {
        landTransactionImpl.saveLandTransaction(landTransaction);
    }

    @Override
    public void savePondTransaction(PondTransaction pondTransaction) throws Exception {
        pondTransactionImpl.savePondTransaction(pondTransaction);
    }

    @Override
    public List<LandTransaction> getLandTransaction() {
        return landTransactionImpl.getLandTransaction();
    }

    @Override
    public List<PondTransaction> getPondTransaction() {
        return pondTransactionImpl.getPondTransaction();
    }

    @Override
    public void mintLand() {
        Random r = new Random();
        List<Land> landList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int seed = r.nextInt(10000);
            String rarity;
            int capacity;
            if (seed < 100) {
                rarity = "legendary";
                capacity = 4;
            } else if (seed < 700) {
                rarity = "rare";
                capacity = 3;
            } else if (seed < 2500) {
                rarity = "uncommon";
                capacity = 2;
            } else {
                rarity = "common";
                capacity = 1;
            }
            Land land = new Land(i + 1, rarity, capacity, System.currentTimeMillis());
            landList.add(land);
        }
        landRepository.saveAll(landList);
    }

    @Override
    public void mintPond() {
        Random r = new Random();
        List<Pond> pondList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            int seed = r.nextInt(10000);
            String rarity;
            int capacity;
            if (seed < 100) {
                rarity = "legendary";
                capacity = 4;
            } else if (seed < 700) {
                rarity = "rare";
                capacity = 3;
            } else if (seed < 2500) {
                rarity = "uncommon";
                capacity = 2;
            } else {
                rarity = "common";
                capacity = 1;
            }
            Pond pond = new Pond(i + 1, rarity, capacity, System.currentTimeMillis());
            pondList.add(pond);
        }
        pondRepository.saveAll(pondList);
    }

    @Override
    public Land getLandById(Integer tokenId) throws Exception {
        Land land = landRepository.findById(tokenId).orElse(new Land(-1, "", 0, 0));
        if (land.getToken_id() == -1) {
            throw new Exception("tokenId does not exist!");
        } else {
            return land;
        }
    }

    @Override
    public Pond getPondById(Integer tokenId) throws Exception {
        Pond pond = pondRepository.findById(tokenId).orElse(new Pond(-1, "", 0, 0));
        if (pond.getToken_id() == -1) {
            throw new Exception("tokenId does not exist!");
        } else {
            return pond;
        }
    }
}
