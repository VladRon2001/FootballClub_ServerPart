package com.mvl.services;

import com.mvl.models.*;
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

    @Autowired
    public FootballWorldService(FootballClubRepository footballClubRepository,
                                TransferMarketRepository transferMarketRepository) {
        this.footballClubRepository = footballClubRepository;
        this.transferMarketRepository = transferMarketRepository;
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
        boolean exist = false;
        for(FootballClub footballClub1: footballClubRepository.findAll()) {
            if(footballClub.getClubName().equals(footballClub1.getClubName())) {
                exist = true;
                break;
            }
        }

        if(!exist) {
            footballClubRepository.save(footballClub);
        }
    }

    private void playGame(FootballClub participantOne, FootballClub participantTwo) {
        final Random random = new Random();
        int diffrenceFootballClub = participantOne.calculateStrongTeam()
                - participantTwo.calculateStrongTeam();

        int resultGame = diffrenceFootballClub + random.nextInt(30) - random.nextInt(30);

        if (resultGame > 10) {
            participantOne.setScore(participantOne.getScore() + 3);
        }
        else if (resultGame < -10) {
            participantTwo.setScore(participantTwo.getScore() + 3);
        }
        else {
            participantOne.setScore(participantOne.getScore() + 1);
            participantTwo.setScore(participantTwo.getScore() + 1);
        }
    }

    @Transactional
    public String results() {
        String result = "";
        for(FootballClub footballClub:footballClubRepository.findAll()) {
            result += footballClub.getClubName() +" " + footballClub.getScore() + "\n";
        }

        return result;
    }

    @Transactional
    public void playRound() {
        for(int i = 0; i < footballClubRepository.findAll().size(); ++i) {
            for(int j = i + 1; j < footballClubRepository.findAll().size(); ++j) {
                playGame(footballClubRepository.findAll().get(i), footballClubRepository.findAll().get(j));
            }
        }
    }

}