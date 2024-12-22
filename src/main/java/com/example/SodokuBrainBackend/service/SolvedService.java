package com.example.SodokuBrainBackend.service;

import com.example.SodokuBrainBackend.model.Solved;
import com.example.SodokuBrainBackend.repository.SolvedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolvedService {
    private final SolvedRepository solvedRepository;

    public SolvedService(SolvedRepository solvedRepository) {
        this.solvedRepository = solvedRepository;
    }

    // Get all solved puzzles for a specific user
    public List<Solved> getSolvedPuzzlesByIdUsername(String username) {
        return solvedRepository.findByIdUsername(username);
    }
}
