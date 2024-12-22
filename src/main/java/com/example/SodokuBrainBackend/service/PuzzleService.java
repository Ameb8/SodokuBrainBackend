package com.example.SodokuBrainBackend.service;

import com.example.SodokuBrainBackend.model.Puzzle;
import com.example.SodokuBrainBackend.repository.PuzzleRepository;
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
