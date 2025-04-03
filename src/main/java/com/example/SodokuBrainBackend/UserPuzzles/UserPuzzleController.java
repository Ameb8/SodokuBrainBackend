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

    /**
     * Endpoint to retrieve user progress for specific puzzle
     *
     * @param puzzleId Id of puzzle
     * @param userId Unique username
     * @return UserPuzzle object
     */
    @GetMapping("/secured/userpuzzle{puzzleId}")
    public ResponseEntity<UserPuzzle> getUserPuzzle(@PathVariable long puzzleId) {
        return ResponseEntity.ok(userPuzzleService.getUserPuzzle(puzzleId));
    }

    /**
     * Updates user progress for specified puzzle
     *
     * @param updatedPuzzle Updated values for specified puzzle
     * @return Response entity
     */
    @PutMapping("/secure/update")
    public ResponseEntity<UserPuzzle> updateUserPuzzle(@RequestBody Attempted updatedPuzzle) {
        UserPuzzle result = userPuzzleService.updateUserPuzzle(updatedPuzzle);

        if (result != null)
            return ResponseEntity.ok(result);

        return ResponseEntity.notFound().build();
    }
}