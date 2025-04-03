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

    /**
     * Gets all created puzzles
     *
     * @return List of all puzzles
     */
    @GetMapping
    public List<PuzzleDTO> getAllPuzzles() {
        return puzzleService.getAllPuzzles();
    }

    /**
     * Gets puzzle with specified ID
     *
     * @param id Unique identifier of puzzle
     * @return PuzzleDTO with passed ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PuzzleDTO> getPuzzleById(@PathVariable Long id) {
        return puzzleService.getPuzzleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Gets random puzzle
     *
     * @return Random PuzzleDTO Object
     */
    @GetMapping("/random")
    public ResponseEntity<PuzzleDTO> getRandomPuzzle() {
        return puzzleService.getRandomPuzzle()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves list of all puzzle's solved by user
     *
     * @param username User's unique username
     * @return List of PuzzleDTO object
     */
    @GetMapping("/secured/solved/{username}")
    public List<SolvedPuzzleDTO> getSolvedPuzzles(@PathVariable String username) {
        return puzzleService.getSolvedPuzzlesByUser(username);
    }

    /**
     * Retrieves all puzzle attempted by specified user
     *
     * @param username User's unique username
     * @return List of PuzzleDTO object
     */
    @GetMapping("/secured/attempted/{username}")
    public List<AttemptedPuzzleDTO> getAttemptedPuzzles(@PathVariable String username) {
        return puzzleService.getAttemptedPuzzlesByUser(username);
    }

    /**
     * Gets metrics for specified puzzle
     *
     * @param id Unique identifier for puzzle
     * @return PuzzleMetricsDTO object for puzzle
     */
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