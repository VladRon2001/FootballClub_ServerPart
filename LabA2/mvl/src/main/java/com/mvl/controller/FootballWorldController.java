package com.mvl.controller;

import com.mvl.models.FootballClub;
import com.mvl.services.FootballWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class FootballWorldController {
    final private FootballWorldService footballWorldService;

    @Autowired
    public FootballWorldController(FootballWorldService footballWorldService) {
        this.footballWorldService = footballWorldService;
    }

    @GetMapping("/playRound")
    public String playRound() {
        return footballWorldService.playRound();
    }

    @PostMapping("/buy")
    FootballClub buy(@RequestBody UUID id) {
        footballWorldService.buyPlayer(id);

        return footballWorldService.getFootballClub(id);
    }

    @PostMapping("/sell")
    FootballClub sell(@RequestBody UUID id) {
        footballWorldService.sellPlayer(id);

        return footballWorldService.getFootballClub(id);
    }
}
