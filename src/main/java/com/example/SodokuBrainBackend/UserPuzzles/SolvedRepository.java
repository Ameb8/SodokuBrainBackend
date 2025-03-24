package com.example.SodokuBrainBackend.UserPuzzles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SolvedRepository extends JpaRepository<Solved, UserPuzzleId> {
}
