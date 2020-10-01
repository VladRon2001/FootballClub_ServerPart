package com.mvl.repository;

import com.mvl.models.FootballClub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public interface FootballClubRepository extends JpaRepository<FootballClub, UUID> {
}
