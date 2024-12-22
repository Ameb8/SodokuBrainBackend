package com.example.SodokuBrainBackend.repository;


import com.example.SodokuBrainBackend.model.Solved;
import com.example.SodokuBrainBackend.model.SolvedId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolvedRepository extends JpaRepository<Solved, SolvedId> {
    List<Solved> findByIdUsername(String username);
}
