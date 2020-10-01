package com.mvl.repository;

import com.mvl.models.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChampionshipRepository extends JpaRepository<Championship, UUID> {
}
