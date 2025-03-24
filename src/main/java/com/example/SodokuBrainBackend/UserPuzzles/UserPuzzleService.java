package com.example.SodokuBrainBackend.UserPuzzles;

import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import com.example.SodokuBrainBackend.Puzzle.PuzzleRepository;
import com.example.SodokuBrainBackend.Users.Users;
import com.example.SodokuBrainBackend.Users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserPuzzleService {
    private final AttemptedRepository attemptedRepository;
    private final SolvedRepository solvedRepository;
    private final PuzzleRepository puzzleRepository;
    private final UsersRepository usersRepository;

    @Autowired
    public UserPuzzleService(AttemptedRepository attemptedRepository, SolvedRepository solvedRepository, PuzzleRepository puzzleRepository, UsersRepository usersRepository) {
        this.attemptedRepository = attemptedRepository;
        this.solvedRepository = solvedRepository;
        this.puzzleRepository = puzzleRepository;
        this.usersRepository = usersRepository;
    }

    public UserPuzzle getUserPuzzle(long puzzleId, String username) {
        UserPuzzleId userPuzzleId = new UserPuzzleId(username, puzzleId);

        //check in Attempted table
        Optional<Attempted> attempted = attemptedRepository.findById(userPuzzleId);
        if (attempted.isPresent()) {
            System.out.println("not found in attempted"); //DEBUG
            return attempted.get();
        }
        //check in Solved table
        Optional<Solved> solved = solvedRepository.findById(userPuzzleId);
        if (solved.isPresent())
            return solved.get();
        

        //check if puzzle exists
        Optional<Puzzle> puzzle = puzzleRepository.findById(userPuzzleId.getPuzzleId());
        Optional<Users> user = usersRepository.findById(userPuzzleId.getUsername());
    /*
        String error = "";
        if(!puzzle.isPresent())
            error += "404 ERROR: puzzle with id " + puzzleId + " does not exist";
        if(!user.isPresent())
            error += "/n404 ERROR: user with username " + username + " does not exist";

        if(error.isEmpty())
            return error;
    */

        Attempted defaultPuzzle = new Attempted(
                userPuzzleId,
                user.get(),
                puzzle.get(),
                puzzle.get().getPuzzleVals(),
                0,
                0,
                LocalDate.now()
        );

        attemptedRepository.save(defaultPuzzle);

        return defaultPuzzle;

    }
/*
    public UserPuzzle updatePuzzle(UserPuzzle userPuzzle) {
        //Optional<Attempted> lastVersion = attemptedRepository.findById(userPuzzle.getId());

        if(userPuzzle.isSolved()) {
            Optional<Attempted> lastVersion = attemptedRepository.findById(userPuzzle.getId());

            if(lastVersion.isPresent()) {
                UserPuzzle solved = lastVersion.get();
                solved.updatePuzzle(userPuzzle);
                //convert to Solved object, add current date as solvedOn

                //save solved object to solved
                //return solved object
            } else {
                Optional<Solved> solved = solvedRepository.findById(userPuzzle.getId());
                //adjust rating on solved to match userPuzzle
                //save userPuzzle to Solved
                //return userPuzzle
            }
        } else {
            Optional<Attempted> lastVersion = attemptedRepository.findById(userPuzzle.getId())

            if(!lastVersion.isPresent())
                return null;

            Attempted last = lastVersion.get();
            last.updatePuzzle(userPuzzle);
            attemptedRepository.save(last);

        }


    }

 */
}
