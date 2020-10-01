package com.mvl.repository;

import com.mvl.models.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface TrainerRepository extends JpaRepository<Trainer, UUID> {
}
