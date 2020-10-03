package com.mvl.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class TransferMarket {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(targetEntity = FootballPlayer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private List<FootballPlayer> playersOnTransfer;

    public TransferMarket(List<FootballPlayer> playersOnTransfer) {
        this.playersOnTransfer = playersOnTransfer;
    }

    public TransferMarket() {playersOnTransfer = new ArrayList<>();
    }


    public TransferMarket addPlayerToTransfer(FootballPlayer footballPlayer) {
       playersOnTransfer.add(footballPlayer);
       return this;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void deletePlayerFromTransfer(FootballPlayer footballPlayer) {
        playersOnTransfer.remove(footballPlayer);
    }

    public FootballPlayer getPlayerByPosition(String position) {
        for(FootballPlayer footballPlayer:playersOnTransfer) {
            if(footballPlayer.getPosition() == position) {
                playersOnTransfer.remove(footballPlayer);
                return footballPlayer;
            }
        }
        return null;
    }

    public List<FootballPlayer> getPlayersOnTransfer() {
        return playersOnTransfer;
    }



    @Override
    public String toString() {
        String listPlayersOnTransfer = "";
        for (FootballPlayer footballPlayer: playersOnTransfer) {
            listPlayersOnTransfer += footballPlayer.toString();
            listPlayersOnTransfer += "\n";
        }

        return listPlayersOnTransfer;
    }
}
