package com.example.SodokuBrainBackend.Puzzle;

import com.example.SodokuBrainBackend.Puzzle.DTO.PuzzleDTO;
import com.example.SodokuBrainBackend.Puzzle.DTO.SolvedPuzzleDTO;
import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import com.example.SodokuBrainBackend.Puzzle.PuzzleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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





    /*
    public PuzzleController(PuzzleService puzzleService) {
        this.puzzleService = puzzleService;
    }

    // Get puzzle by ID and return the whole model object
    @GetMapping("/{id}")
    public ResponseEntity<Puzzle> getPuzzleById(@PathVariable Long id) {
        Optional<Puzzle> puzzleOptional = puzzleService.getPuzzleById(id);
        if (puzzleOptional.isPresent()) {
            return ResponseEntity.ok(puzzleOptional.get());  // Returning the entire Puzzle object
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a random puzzle and return the whole model object
    @GetMapping("/random")
    public ResponseEntity<Puzzle> getRandomPuzzle() {
        Puzzle puzzle = puzzleService.getRandomPuzzle();
        return ResponseEntity.ok(puzzle);  // Returning the entire Puzzle object
    }

    // Upload a new puzzle and return the uploaded puzzle
    /*@PostMapping
    public ResponseEntity<Puzzle> uploadPuzzle(@RequestBody Puzzle puzzle) {
        Puzzle savedPuzzle = puzzleService.savePuzzle(puzzle);
        return ResponseEntity.status(201).body(savedPuzzle);  // Returning the entire Puzzle object
    }*/
}