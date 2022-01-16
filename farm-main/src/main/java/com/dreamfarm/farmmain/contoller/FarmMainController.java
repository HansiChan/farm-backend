package com.dreamfarm.farmmain.contoller;

import com.dreamfarm.farmmain.entity.*;
import com.dreamfarm.farmmain.service.impl.FarmMainImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "FarmMain", tags = "FarmMain")
@RestController
@EnableAsync
public class FarmMainController {

    private final FarmMainImpl farmMainImpl;

    private final Gson gson = new Gson();

    @Autowired
    public FarmMainController(FarmMainImpl farmMainImpl) {
        this.farmMainImpl = farmMainImpl;
    }

    @ApiOperation("save land transaction")
    @RequestMapping(value = "${config.root-url}/save_land_transaction", method = RequestMethod.POST)
    @ResponseBody
    String saveLandTransaction(@RequestBody String req) {
        JsonObject reqJson = JsonParser.parseString(req).getAsJsonObject();

        try {
            LandTransaction landTransaction = gson.fromJson(reqJson, LandTransaction.class);

            farmMainImpl.saveLandTransaction(landTransaction);
            return gson.toJson(new ResponseEntity(0, "success!"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("save pond transaction")
    @RequestMapping(value = "${config.root-url}/save_pond_transaction", method = RequestMethod.POST)
    @ResponseBody
    String savePondTransaction(@RequestBody String req) {
        JsonObject reqJson = JsonParser.parseString(req).getAsJsonObject();

        try {
            PondTransaction pondTransaction = gson.fromJson(reqJson, PondTransaction.class);

            farmMainImpl.savePondTransaction(pondTransaction);
            return gson.toJson(new ResponseEntity(0, "success!"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("get land transaction list")
    @RequestMapping(value = "${config.root-url}/get_land_transaction", method = RequestMethod.GET)
    @ResponseBody
    String getLandTransaction() {
        try {

            List<LandTransaction> transactionList = farmMainImpl.getLandTransaction();
            return gson.toJson(new ResponseEntity(0, transactionList));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("get pond transaction list")
    @RequestMapping(value = "${config.root-url}/get_pond_transaction", method = RequestMethod.GET)
    @ResponseBody
    String getPondTransaction() {
        try {

            List<PondTransaction> transactionList = farmMainImpl.getPondTransaction();
            return gson.toJson(new ResponseEntity(0, transactionList));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("mint land nft list")
    @RequestMapping(value = "${config.root-url}/mint_land_nft", method = RequestMethod.POST)
    @ResponseBody
    String mintLandNft() {
        try {
            farmMainImpl.mintLand();
            return gson.toJson(new ResponseEntity(0, "success!"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("mint pond nft list")
    @RequestMapping(value = "${config.root-url}/mint_pond_nft", method = RequestMethod.POST)
    @ResponseBody
    String mintPond() {
        try {
            farmMainImpl.mintPond();
            return gson.toJson(new ResponseEntity(0, "success!"));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("find land nft by tokenId")
    @RequestMapping(value = "${config.root-url}/find_land_nft", method = RequestMethod.POST)
    @ResponseBody
    String findLandNft(@RequestParam Integer tokenId) {
        try {
            Land land = farmMainImpl.getLandById(tokenId);
            return gson.toJson(new ResponseEntity(0, land));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }

    @ApiOperation("find pond nft by tokenId")
    @RequestMapping(value = "${config.root-url}/find_pond_nft", method = RequestMethod.POST)
    @ResponseBody
    String findPondNft(@RequestParam Integer tokenId) {
        try {
            Pond pond = farmMainImpl.getPondById(tokenId);
            return gson.toJson(new ResponseEntity(0, pond));
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new ResponseEntity(-1, e.getMessage()));
        }
    }
}
