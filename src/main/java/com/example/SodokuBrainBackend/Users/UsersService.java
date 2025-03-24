package com.example.SodokuBrainBackend.Users;


import com.example.SodokuBrainBackend.Puzzle.DTO.AttemptedPuzzleDTO;
import com.example.SodokuBrainBackend.Users.DTO.LeaderboardDTO;
import com.example.SodokuBrainBackend.Users.Users;
import com.example.SodokuBrainBackend.Users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public Users saveUser(Users user) {return usersRepository.save(user);}

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Optional<Users> getUserByUsername(String username) {
        return usersRepository.findById(username);
    }

    public Optional<Users> getUserByEmail(String email) {
        return Optional.ofNullable(usersRepository.findByEmail(email));
    }

    public void deleteUserByUsername(String username) {
        usersRepository.deleteById(username);
    }

    @Transactional
    public List<LeaderboardDTO> getLeaderboard(long pageOffset, int pageSize) {
        List<Object[]> leaders = usersRepository.GetLeaderboard(pageOffset * pageSize, pageSize);
        List<LeaderboardDTO> leaderboard = new ArrayList<>();

        for(Object[] data : leaders) {
            String username = (String) data[0];
            long puzzlesSolved = (long) data[1];
            double avgSolveTime = ((BigDecimal) data[2]).doubleValue();
            double avgHintsUsed = ((BigDecimal) data[3]).doubleValue();
            LeaderboardDTO user = new LeaderboardDTO(username, puzzlesSolved, avgSolveTime, avgHintsUsed);
            leaderboard.add(user);
        }

        return leaderboard;
    }

    @Transactional
    public long getNumUsers() {
        List<Object[]> users = usersRepository.GetNumUsers();
        Object[] data = users.getFirst();

        return (long) data[0];
    }
}