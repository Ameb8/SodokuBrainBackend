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

    @GetMapping("/{puzzleId}/{username}")
    public ResponseEntity<UserPuzzle> getUserPuzzle(@PathVariable long puzzleId, @PathVariable String username) {
        return ResponseEntity.ok(userPuzzleService.getUserPuzzle(puzzleId, username));
    }

    /*@PutMapping("/user")
    public ResponseEntity<String> updateUserPuzzle(@RequestBody UserPuzzle userPuzzle) {
        userPuzzleService.updatePuzzle(userPuzzle);
        return ResponseEntity.ok("User puzzle updated successfully!");
    }

     */
}