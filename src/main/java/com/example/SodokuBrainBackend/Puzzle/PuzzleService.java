package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.DTO.PuzzleDTO;
import com.example.SodokuBrainBackend.Puzzle.DTO.SolvedPuzzleDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PuzzleService {
    @Autowired
    private PuzzleRepository puzzleRepository;

    private PuzzleDTO toPuzzleDTO(Puzzle puzzle) {
        return new PuzzleDTO(puzzle.getPuzzleId(), puzzle.getPuzzleVals(), puzzle.getSolutionVals());
    }

    // Get puzzle by ID
    public Optional<PuzzleDTO> getPuzzleById(Long id) {
        return puzzleRepository.findById(id).map(this::toPuzzleDTO);
    }

    // Get a random puzzle
    public Optional<PuzzleDTO> getRandomPuzzle() {
        List<Puzzle> puzzles = puzzleRepository.findAll();
        if (puzzles.isEmpty()) return Optional.empty();

        Random random = new Random();
        Puzzle randomPuzzle = puzzles.get(random.nextInt(puzzles.size()));
        return Optional.of(toPuzzleDTO(randomPuzzle));
    }

    // Get all puzzles
    public List<PuzzleDTO> getAllPuzzles() {
        return puzzleRepository.findAll()
                .stream()
                .map(this::toPuzzleDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<SolvedPuzzleDTO> getSolvedPuzzlesByUser(String username) {
        List<Object[]> puzzleData = puzzleRepository.GetSolvedPuzzles(username);

        List<SolvedPuzzleDTO> solvedPuzzles = new ArrayList<>();

        for (Object[] data : puzzleData) {

            //Puzzle puzzle = (Puzzle) data[0]; // Assuming puzzle is the first element

            Integer puzzleId = (Integer) data[0];
            String puzzleVals = (String) data[1];
            String solutionVals = (String) data[2];
            int secondsToSolve = (Integer) data[3];
            int hintsUsed = (Integer) data[4];
            Byte rating = (Byte) data[5];
            LocalDate startedOn = ((java.sql.Date) data[6]).toLocalDate();
            LocalDate solvedOn = ((java.sql.Date) data[7]).toLocalDate();

            //Integer ratingInt = Integer.valueOf(rating);
            Long puzzleIdLong = puzzleId.longValue();

            SolvedPuzzleDTO solvedPuzzle = toSolvedPuzzleDTO(puzzleIdLong, puzzleVals, solutionVals, secondsToSolve, hintsUsed, rating, startedOn, solvedOn);
            solvedPuzzles.add(solvedPuzzle);
        }

        return solvedPuzzles;
    }

    private SolvedPuzzleDTO toSolvedPuzzleDTO(Long puzzleId, String puzzleVals, String solutionVals, int secondsToSolve, int hintsUsed, Byte rating, LocalDate startedOn, LocalDate solvedOn) {
        return new SolvedPuzzleDTO(
                puzzleId,
                puzzleVals,
                solutionVals,
                secondsToSolve,
                hintsUsed,
                rating,
                startedOn,
                solvedOn
        );
    }
}












/*
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
*/