package com.example.SodokuBrainBackend.Puzzle.DTO;

public class AttemptedPuzzleDTO extends PuzzleDTO {
    public AttemptedPuzzleDTO(Long puzzleId, String puzzleVals, String solutionVals) {
        super(puzzleId, puzzleVals, solutionVals);
    }
}
