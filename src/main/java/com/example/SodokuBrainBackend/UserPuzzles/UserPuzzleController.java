package com.example.SodokuBrainBackend.UserPuzzles;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/puzzles")
public class UserPuzzleController {

    private final UserPuzzleService userPuzzleService;

    public UserPuzzleController(UserPuzzleService userPuzzleService) {
        this.userPuzzleService = userPuzzleService;
    }

    @GetMapping("/secure/{puzzleId}/{username}")
    public ResponseEntity<UserPuzzle> getUserPuzzle(@PathVariable long puzzleId, @PathVariable String username) {
        return ResponseEntity.ok(userPuzzleService.getUserPuzzle(puzzleId, username));
    }

    @PutMapping("/secure/update")
    public ResponseEntity<UserPuzzle> updateUserPuzzle(@RequestBody Attempted updatedPuzzle) {
        UserPuzzle result = userPuzzleService.updateUserPuzzle(updatedPuzzle);

        if (result != null)
            return ResponseEntity.ok(result);

        return ResponseEntity.notFound().build();
    }
}