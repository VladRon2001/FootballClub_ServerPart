package com.mvl.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mvl.models.Championship;
import com.mvl.models.DTO.ChampionshipDTO;
import com.mvl.models.DTO.FootballClubDTO;
import com.mvl.models.DTO.TranferMarketDTO;
import com.mvl.models.FootballClub;
import com.mvl.models.FootballPlayer;
import com.mvl.services.FootballWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class FootballWorldController {
    final private FootballWorldService footballWorldService;

    @Autowired
    public FootballWorldController(FootballWorldService footballWorldService) {
        this.footballWorldService = footballWorldService;
    }

    @GetMapping("/playRound")
    public @ResponseBody String playRound() {
        return footballWorldService.playRound();
    }

    @PostMapping("/buy/{id}")
    public ResponseEntity<FootballClub> buy(@PathVariable UUID id) {
        footballWorldService.buyPlayer(id);

        return ResponseEntity.ok(footballWorldService.getFootballClub(id));
    }

    @PostMapping("/sell/{id}")
    public FootballClub sell(@PathVariable UUID id) {
        footballWorldService.sellPlayer(id);

        return footballWorldService.getFootballClub(id);
    }

    @PostMapping("footballWorld/createClub")
    public ResponseEntity<Void> createClub(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        FootballClubDTO footballPlayersDto = gson.fromJson(deliverJson, FootballClubDTO.class);

        footballWorldService.createClub(new FootballClub(footballPlayersDto.getClubName(),
                footballPlayersDto.getFootballTeam(), footballPlayersDto.getTrainer(),
                footballPlayersDto.getBudget()));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("footballWorld/createChampionship")
    public ResponseEntity<Void> createChampionship(@RequestBody String deliverJson) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        ChampionshipDTO championshipDTO= gson.fromJson(deliverJson, ChampionshipDTO.class);

        footballWorldService.createChampionship(new Championship(
                championshipDTO.getParticipants(), championshipDTO.getScores()));

        return ResponseEntity.noContent().build();
    }

}
