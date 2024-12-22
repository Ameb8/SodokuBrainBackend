package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import com.example.SodokuBrainBackend.Puzzle.PuzzleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PuzzleService {
    private final PuzzleRepository puzzleRepository;

    public PuzzleService(PuzzleRepository puzzleRepository) {
        this.puzzleRepository = puzzleRepository;
    }

    public Puzzle uploadPuzzle(String puzzleVals) {
        if(puzzleVals.length() != 81) {
            throw new IllegalArgumentException();
        }

        Puzzle puzzle = new Puzzle();
        puzzle.setPuzzleVals(puzzleVals);

        return puzzleRepository.save(puzzle);
    }

    public Optional<Puzzle> getPuzzleById(Long id) {
        return puzzleRepository.findById(id);
    }

    public Puzzle getRandomPuzzle() {
        return puzzleRepository.findRandomPuzzle();
    }
}
