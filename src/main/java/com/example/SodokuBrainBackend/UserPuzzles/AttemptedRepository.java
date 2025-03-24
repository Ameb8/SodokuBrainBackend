package com.example.SodokuBrainBackend.UserPuzzles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptedRepository extends JpaRepository<Attempted, UserPuzzleId> {
}
