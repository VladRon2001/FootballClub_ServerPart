package com.mvl.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvl.models.DTO.TranferMarketDTO;
import com.mvl.models.DTO.FootballPlayerDTO;
import com.mvl.models.FootballPlayer;
import com.mvl.services.TransferMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferMarketController {
    final private TransferMarkerService transferMarkerService;

    @Autowired
    public TransferMarketController(TransferMarkerService transferMarkerService) {
        this.transferMarkerService = transferMarkerService;
    }

    @PostMapping("transferMarkets/fill")
    public ResponseEntity<Void> create(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        TranferMarketDTO footballPlayersDto = gson.fromJson(deliverJson, TranferMarketDTO.class);
        List<FootballPlayer> footballPlayers = footballPlayersDto.getFootballPlayerList();

        transferMarkerService.create(footballPlayers);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/transferMarket")
    public @ResponseBody TranferMarketDTO allTr() {
        TranferMarketDTO footballPlayers = new TranferMarketDTO();
        footballPlayers.setFootballPlayerList(
                transferMarkerService.getAll().getPlayersOnTransfer());
        return footballPlayers;
    }

    @PostMapping("/transferMarket/add")
    public ResponseEntity<Void> addPlayer(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        FootballPlayerDTO player = gson.fromJson(deliverJson, FootballPlayerDTO.class);

        FootballPlayer footballPlayer = new FootballPlayer(player.getName(),
                player.getRatingScore(), player.getRatingScore(), player.getPosition());

        transferMarkerService.add(footballPlayer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/transferMarket/delete")
    public ResponseEntity<Void> deletePlayer(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        FootballPlayerDTO player = gson.fromJson(deliverJson, FootballPlayerDTO.class);

        FootballPlayer footballPlayer = new FootballPlayer(player.getName(),
                player.getRatingScore(), player.getRatingScore(), player.getPosition());

        transferMarkerService.delete(footballPlayer);
        return ResponseEntity.noContent().build();
    }

}
