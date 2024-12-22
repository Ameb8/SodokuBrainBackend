package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PuzzleRepository  extends JpaRepository<Puzzle, Long> {
    @Query(value = "SELECT * FROM puzzle ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Puzzle findRandomPuzzle();
}
