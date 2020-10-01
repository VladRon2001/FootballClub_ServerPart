package com.mvl.models;

import javax.persistence.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

@Entity
public class TransferMarket {
    @Id
    @GeneratedValue
    private UUID id;

    @OneToMany(targetEntity = FootballPlayer.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private final List<FootballPlayer> playersOnTransfer;

    public TransferMarket() {
        playersOnTransfer = new ArrayList<>(0);
        fillTransferMarket();
    }

    public void addPlayerToTransfer(FootballPlayer footballPlayer) {
       playersOnTransfer.add(footballPlayer);
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


    public void fillTransferMarket(){
        String url = "jdbc:postgresql://localhost:5433/postgres";
        String user = "postgres";
        String password = "13102001";


        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM player_list");
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                String stringPlayerName = rs.getString(1);
                int ratingScore = rs.getInt(2);
                String titlePosition = rs.getString(3);
                int age = rs.getInt(4);

                addPlayerToTransfer(
                        new FootballPlayer(stringPlayerName, age, ratingScore, titlePosition));
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(TransferMarket.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
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
