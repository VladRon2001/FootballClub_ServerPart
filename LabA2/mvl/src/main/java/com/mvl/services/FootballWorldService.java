package com.mvl.services;

import com.mvl.models.*;
import com.mvl.repository.ChampionshipRepository;
import com.mvl.repository.FootballClubRepository;
import com.mvl.repository.TransferMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.UUID;

@Service
public class FootballWorldService {
    final private FootballClubRepository footballClubRepository;
    final private TransferMarketRepository transferMarketRepository;
    final private ChampionshipRepository championshipRepository;

    @Autowired
    public FootballWorldService(FootballClubRepository footballClubRepository,
                                TransferMarketRepository transferMarketRepository,
                                ChampionshipRepository championshipRepository) {
        this.footballClubRepository = footballClubRepository;
        this.transferMarketRepository = transferMarketRepository;
        this.championshipRepository = championshipRepository;
    }

    @Transactional
    private void fillFootballClub(UUID clubId, UUID transferId) {
        footballClubRepository.findById(clubId).get().
                addPlayerToFootballTeam(transferMarketRepository.findById(transferId).get()
                        .getPlayerByPosition("GK"));
        footballClubRepository.findById(clubId).get().
                addPlayerToFootballTeam(transferMarketRepository.findById(transferId).get()
                        .getPlayerByPosition("DF"));
        footballClubRepository.findById(clubId).get().
                addPlayerToFootballTeam(transferMarketRepository.findById(transferId).get()
                        .getPlayerByPosition("CM"));;
        footballClubRepository.findById(clubId).get().
                addPlayerToFootballTeam(transferMarketRepository.findById(transferId).get()
                        .getPlayerByPosition("ST"));
    }

    @Transactional
    public String playRound() {
        championshipRepository.findAll().get(0).playRound();
        return championshipRepository.findAll().get(0).toString();
    }

    @Transactional
    public void buyPlayer(UUID ftClubId) {
        int countPlayersOnTransfer = transferMarketRepository.findAll().get(0)
                .getPlayersOnTransfer().size();

        Random random = new Random();

        int indexRandomPlayer = random.nextInt(countPlayersOnTransfer) - 1;

        FootballPlayer footballPlayer =  transferMarketRepository.findAll().get(0)
                .getPlayersOnTransfer().get(indexRandomPlayer);

        Deal deal = new Deal(footballPlayer, TypeDeal.Buying);

        if(footballClubRepository.findById(ftClubId).get().getBudget() > deal.getCostDeal()) {
            if(deal.makeDeal(transferMarketRepository.findAll().get(0),
                    footballClubRepository.findById(ftClubId).get())) {
                footballClubRepository.findById(ftClubId).get().setBudget(
                        footballClubRepository.findById(ftClubId).get().getBudget()
                                - deal.getCostDeal());
            }
        }
    }

    @Transactional
    public FootballClub getFootballClub(UUID clubId) {
        return footballClubRepository.findById(clubId).get();
    }

    @Transactional
    public void sellPlayer(UUID ftClubId) {
        if (footballClubRepository.findById(ftClubId).get().getFootballTeam().size() > 4) {
            Random random = new Random();

            int indexRandomPlayer = random.nextInt(footballClubRepository.findById(ftClubId).get()
                    .getFootballTeam().size());
            FootballPlayer footballPlayer = footballClubRepository.findById(ftClubId).get()
                    .getFootballTeam().get(indexRandomPlayer);

            Deal deal = new Deal(footballPlayer, TypeDeal.Selling);
            deal.makeDeal(transferMarketRepository.findAll().get(0),
                    footballClubRepository.findById(ftClubId).get());

            if(deal.makeDeal(transferMarketRepository.findAll().get(0),
                    footballClubRepository.findById(ftClubId).get())) {
                footballClubRepository.findById(ftClubId).get().setBudget(
                        footballClubRepository.findById(ftClubId).get().getBudget()
                                + deal.getCostDeal());
            }
        }
    }

    @Transactional
    public void createClub(FootballClub footballClub) {
        footballClubRepository.save(footballClub);
    }

    @Transactional
    public void createChampionship(Championship championship) {
        championshipRepository.save(championship);
    }

}