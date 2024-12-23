package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.DTO.PuzzleDTO;
import com.example.SodokuBrainBackend.Puzzle.DTO.SolvedPuzzleDTO;
import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PuzzleRepository  extends JpaRepository<Puzzle, Long> {
    @Procedure(name = "GetSolvedPuzzles")
    List<Object[]> GetSolvedPuzzles(@Param("username") String username);

    //@Query(value = "SELECT * FROM puzzle ORDER BY RAND() LIMIT 1", nativeQuery = true)
    //Puzzle findRandomPuzzle();
}
