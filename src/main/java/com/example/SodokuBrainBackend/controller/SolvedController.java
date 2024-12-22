package com.example.SodokuBrainBackend.controller;

import com.example.SodokuBrainBackend.model.Solved;
import com.example.SodokuBrainBackend.service.SolvedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solved")
public class SolvedController {

    private final SolvedService solvedService;

    public SolvedController(SolvedService solvedService) {
        this.solvedService = solvedService;
    }


    @GetMapping("/user/{username}")
    public ResponseEntity<List<Solved>> getSolvedPuzzlesByIdUsername(@PathVariable String username) {
        List<Solved> solvedPuzzles = solvedService.getSolvedPuzzlesByIdUsername(username);
        return ResponseEntity.ok(solvedPuzzles);
    }
}
