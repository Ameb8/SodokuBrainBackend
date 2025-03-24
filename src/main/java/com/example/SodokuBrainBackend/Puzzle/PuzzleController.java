package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/puzzles")
public class PuzzleController {
    @Autowired
    private PuzzleService puzzleService;

    // Get all puzzles
    @GetMapping
    public List<PuzzleDTO> getAllPuzzles() {
        return puzzleService.getAllPuzzles();
    }

    // Get puzzle by ID
    @GetMapping("/{id}")
    public ResponseEntity<PuzzleDTO> getPuzzleById(@PathVariable Long id) {
        return puzzleService.getPuzzleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get a random puzzle
    @GetMapping("/random")
    public ResponseEntity<PuzzleDTO> getRandomPuzzle() {
        return puzzleService.getRandomPuzzle()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/solved/{username}")
    public List<SolvedPuzzleDTO> getSolvedPuzzles(@PathVariable String username) {
        return puzzleService.getSolvedPuzzlesByUser(username);
    }

    @GetMapping("/attempted/{username}")
    public List<AttemptedPuzzleDTO> getAttemptedPuzzles(@PathVariable String username) {
        return puzzleService.getAttemptedPuzzlesByUser(username);
    }

    @GetMapping("/{id}/metrics")
    public ResponseEntity<PuzzleMetricsDTO> getPuzzleMetrics(@PathVariable Long id) {
        PuzzleMetricsDTO metrics = puzzleService.getPuzzleMetricsDTO(id);

        return ResponseEntity.ok(metrics);
    }
/*
    @GetMapping("/{id}/{user}")
    public PuzzleStateDTO getUserPuzzle(@PathVariable long id, @PathVariable String username){
        return puzzleService.getUserPuzzle(id, username);
    }
*/
    @PostMapping
    public ResponseEntity<Puzzle> createPuzzle(@RequestBody Puzzle puzzle) {
        Puzzle savedPuzzle = puzzleService.savePuzzle(puzzle);
        return ResponseEntity.ok(savedPuzzle);
    }
}