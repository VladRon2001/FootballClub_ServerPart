package com.mvl.services;

import com.mvl.models.FootballPlayer;
import com.mvl.models.TransferMarket;
import com.mvl.repository.TransferMarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class TransferMarkerService {
    final private TransferMarketRepository transferMarketRepository;

    @Autowired
    public TransferMarkerService(TransferMarketRepository transferMarketRepository) {
        this.transferMarketRepository = transferMarketRepository;
    }

    @Transactional
    public TransferMarket getOne(UUID trMatketId) {
        return transferMarketRepository.findById(trMatketId).get();
    }

    @Transactional
    public void addPlayer(FootballPlayer footballPlayer, UUID trMarketId) {
        transferMarketRepository.findById(trMarketId).get().addPlayerToTransfer(footballPlayer);
    }
}
