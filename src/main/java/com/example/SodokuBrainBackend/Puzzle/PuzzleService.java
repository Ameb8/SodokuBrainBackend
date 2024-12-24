package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.DTO.AttemptedPuzzleDTO;
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

    //gets puzzle by ID
    public Optional<PuzzleDTO> getPuzzleById(Long id) {
        return puzzleRepository.findById(id).map(this::toPuzzleDTO);
    }

    //gets a random puzzle
    public Optional<PuzzleDTO> getRandomPuzzle() {
        List<Puzzle> puzzles = puzzleRepository.findAll();
        if (puzzles.isEmpty()) return Optional.empty();

        Random random = new Random();
        Puzzle randomPuzzle = puzzles.get(random.nextInt(puzzles.size()));
        return Optional.of(toPuzzleDTO(randomPuzzle));
    }

    //geta all puzzles
    public List<PuzzleDTO> getAllPuzzles() {
        return puzzleRepository.findAll()
                .stream()
                .map(this::toPuzzleDTO)
                .collect(Collectors.toList());
    }

    //gets list of solved puzzles for a user
    @Transactional
    public List<SolvedPuzzleDTO> getSolvedPuzzlesByUser(String username) {
        List<Object[]> puzzleData = puzzleRepository.GetSolvedPuzzles(username);
        List<SolvedPuzzleDTO> solvedPuzzles = new ArrayList<>();

        for (Object[] data : puzzleData) {
            Integer puzzleId = (Integer) data[0];
            String puzzleVals = (String) data[1];
            String solutionVals = (String) data[2];
            Integer secondsToSolve = (Integer) data[3];
            Integer hintsUsed = (Integer) data[4];
            Byte rating = (Byte) data[5];
            LocalDate startedOn = ((java.sql.Date) data[6]).toLocalDate();
            LocalDate solvedOn = ((java.sql.Date) data[7]).toLocalDate();

            //Integer ratingInt = Integer.valueOf(rating);
            Long puzzleIdLong = puzzleId.longValue();

            if(secondsToSolve == null)
                secondsToSolve = 0;
            if(hintsUsed == null)
                hintsUsed = 0;

            SolvedPuzzleDTO solvedPuzzle = new SolvedPuzzleDTO(puzzleIdLong, puzzleVals, solutionVals, secondsToSolve, hintsUsed, rating, startedOn, solvedOn);
            solvedPuzzles.add(solvedPuzzle);
        }

        return solvedPuzzles;
    }

    //gets list of attempted puzzle by user
    @Transactional
    public List<AttemptedPuzzleDTO> getAttemptedPuzzlesByUser(String username) {
        List<Object[]> puzzleData = puzzleRepository.GetAttemptedPuzzles(username);
        List<AttemptedPuzzleDTO> attemptedPuzzles = new ArrayList<>();

        for (Object[] data : puzzleData) {
            Long puzzleId = Long.valueOf((Integer) data[0]);
            String puzzleVals = (String) data[1];
            String solutionVals = (String) data[2];
            Integer secondsWorkedOn = (Integer) data[3];
            Integer hintsUsed = (Integer) data[4];
            LocalDate startedOn = ((java.sql.Date) data[5]).toLocalDate();

            if(secondsWorkedOn == null)
                secondsWorkedOn = 0;
            if(hintsUsed == null)
                hintsUsed = 0;

            AttemptedPuzzleDTO attemptedPuzzle = new AttemptedPuzzleDTO(puzzleId, puzzleVals, solutionVals, secondsWorkedOn, hintsUsed, startedOn);
            attemptedPuzzles.add(attemptedPuzzle);
        }

        return attemptedPuzzles;
    }
}