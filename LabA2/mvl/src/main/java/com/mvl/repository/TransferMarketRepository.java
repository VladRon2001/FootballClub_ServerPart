package com.mvl.repository;

import com.mvl.models.Trainer;
import com.mvl.models.TransferMarket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransferMarketRepository extends JpaRepository<TransferMarket, UUID> {
}
