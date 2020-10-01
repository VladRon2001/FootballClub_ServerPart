package com.mvl.controller;

import com.mvl.models.FootballPlayer;
import com.mvl.models.TransferMarket;
import com.mvl.services.TransferMarkerService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class TransferMarketController {
    final private TransferMarkerService transferMarkerService;

    public TransferMarketController(TransferMarkerService transferMarkerService) {
        this.transferMarkerService = transferMarkerService;
    }

    @GetMapping("/transferMarket/{id}")
    TransferMarket all(@PathVariable UUID id) {
        return transferMarkerService.getOne(id);
    }

    @PostMapping("/transferMarket/{id}")
    TransferMarket addPlayer(@PathVariable UUID id, @RequestBody FootballPlayer footballPlayer) {
         transferMarkerService.addPlayer(footballPlayer, id);
         return transferMarkerService.getOne(id);
    }
}
