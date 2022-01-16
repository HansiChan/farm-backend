package com.dreamfarm.farmmain.service.impl;

import com.dreamfarm.farmmain.entity.LandTransaction;
import com.dreamfarm.farmmain.service.LandTransactionRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Service
public class LandTransactionImpl {

    private final LandTransactionRepository landTransactionRepository;
    private final RestTemplate restTemplate;

    @Value("${config.land-contract}")
    private String contract;
    @Value("${config.transactionUrl}")
    private String apiUrl;

    @Autowired
    public LandTransactionImpl(LandTransactionRepository landTransactionRepository, RestTemplateBuilder builder) {
        this.landTransactionRepository = landTransactionRepository;
        this.restTemplate = builder.build();
    }

    void saveLandTransaction(LandTransaction landTransaction) throws Exception {

        String url = String.format(apiUrl, contract, landTransaction.getBuyer());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        org.springframework.http.ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        JsonObject entityJson = JsonParser.parseString(Objects.requireNonNull(entity.getBody())).getAsJsonObject();
        JsonArray transactionList = entityJson.getAsJsonArray("result");
        for (JsonElement transaction : transactionList) {
            String transactionHash = landTransaction.getTransaction_hash();
            String addressHash = transaction.getAsJsonObject().get("hash").toString().replace("\"", "");
            if (addressHash.equals(transactionHash)) {
                landTransaction.setToken_id(transaction.getAsJsonObject().get("tokenID").getAsInt());
            }
        }
        if (landTransaction.getToken_id() == 0) {
            throw new Exception("This transaction:" +
                    landTransaction.getTransaction_hash() +
                    " does not related to a tokenId,please confirm!");
        }
        landTransactionRepository.save(landTransaction);
    }

    List<LandTransaction> getLandTransaction() {
        return landTransactionRepository.findAll();
    }
}
