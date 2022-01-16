package com.dreamfarm.farmmain.service.impl;

import com.dreamfarm.farmmain.entity.PondTransaction;
import com.dreamfarm.farmmain.service.PondTransactionRepository;
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
public class PondTransactionImpl {

    private final PondTransactionRepository pondTransactionRepository;
    private final RestTemplate restTemplate;

    @Value("${config.pond-contract}")
    private String contract;
    @Value("${config.transactionUrl}")
    private String apiUrl;

    @Autowired
    public PondTransactionImpl(PondTransactionRepository pondTransactionRepository, RestTemplateBuilder builder) {
        this.pondTransactionRepository = pondTransactionRepository;
        this.restTemplate = builder.build();
    }

    void savePondTransaction(PondTransaction pondTransaction) throws Exception {

        String url = String.format(apiUrl, contract, pondTransaction.getBuyer());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        org.springframework.http.ResponseEntity<String> entity = restTemplate.postForEntity(url, request, String.class);
        JsonObject entityJson = JsonParser.parseString(Objects.requireNonNull(entity.getBody())).getAsJsonObject();
        JsonArray transactionList = entityJson.getAsJsonArray("result");
        for (JsonElement transaction : transactionList) {
            String transactionHash = pondTransaction.getTransaction_hash();
            String addressHash = transaction.getAsJsonObject().get("hash").toString().replace("\"", "");
            if (addressHash.equals(transactionHash)) {
                pondTransaction.setToken_id(transaction.getAsJsonObject().get("tokenID").getAsInt());
            }
        }
        if (pondTransaction.getToken_id() == 0) {
            throw new Exception("This transaction:" +
                    pondTransaction.getTransaction_hash() +
                    " does not related to a tokenId,please confirm!");
        }
        pondTransactionRepository.save(pondTransaction);
    }

    List<PondTransaction> getPondTransaction() {
        return pondTransactionRepository.findAll();
    }
}
