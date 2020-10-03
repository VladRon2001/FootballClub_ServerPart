package com.mvl.services;

import com.mvl.models.FootballPlayer;
import com.mvl.models.TransferMarket;
import com.mvl.repository.TransferMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class TransferMarkerService {
    final private TransferMarketRepository transferMarketRepository;

    @Autowired
    public TransferMarkerService(TransferMarketRepository transferMarketRepository) {
        this.transferMarketRepository = transferMarketRepository;
    }

    @Transactional
    public void create(List<FootballPlayer> footballPlayers) {
        transferMarketRepository.save(new TransferMarket(footballPlayers));
    }

    @Transactional
    public void add(FootballPlayer footballPlayer) {
        transferMarketRepository.findAll().get(0).addPlayerToTransfer(footballPlayer);
    }

    @Transactional
    public void delete(FootballPlayer footballPlayer) {
        transferMarketRepository.findAll().get(0).deletePlayerFromTransfer(footballPlayer);
    }
    @Transactional
    public TransferMarket getAll() {
        return transferMarketRepository.findAll().get(0);
    }
}
