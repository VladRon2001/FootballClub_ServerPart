package com.mvl.models;

import java.util.Random;

public class Deal {
    private final int costDeal;
    final private TypeDeal typeDeal;
    final private FootballPlayer playerInDeal;

    public Deal(FootballPlayer player, TypeDeal typeDeal) {
        costDeal = player.calculateCost();
        this.typeDeal = typeDeal;
        playerInDeal = player;
    }

    public boolean makeDeal(TransferMarket transferMarket, FootballClub footballClub) {
        Random random = new Random();
        boolean doDeal = random.nextBoolean();

        if(doDeal) {
            if(typeDeal == TypeDeal.Buying) {
                footballClub.addPlayerToFootballTeam(playerInDeal);
                transferMarket.deletePlayerFromTransfer(playerInDeal);
            }
            else {
                transferMarket.addPlayerToTransfer(playerInDeal);
                footballClub.deletePlayerToFootballTeam(playerInDeal);
            }
            return true;
        }
        return false;
    }

    public int getCostDeal() {
        return costDeal;
    }
}