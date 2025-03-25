package com.example.SodokuBrainBackend.UserPuzzles;

import com.example.SodokuBrainBackend.Puzzle.Puzzle;
import com.example.SodokuBrainBackend.Puzzle.PuzzleRepository;
import com.example.SodokuBrainBackend.Users.Users;
import com.example.SodokuBrainBackend.Users.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Transactional
    public UserPuzzle updateUserPuzzle(Attempted updatePuzzle) {
        UserPuzzleId userPuzzleId = updatePuzzle.getId();
        Optional<Solved> solvedOpt = solvedRepository.findById(userPuzzleId);

        if(solvedOpt.isPresent())
            return solvedOpt.get();

        Optional<Attempted> attemptedOpt = attemptedRepository.findById(userPuzzleId);

        if(attemptedOpt.isPresent()) {
            Attempted attempt = attemptedOpt.get();
            Attempted updatedPuzzle = updatePuzzle(attempt, updatePuzzle);

            if(updatedPuzzle != null && isSolved(updatedPuzzle)) { //puzzle just solved
                Solved newUpdate = new Solved(updatedPuzzle);
                solvedRepository.save(newUpdate);
                attemptedRepository.delete(attempt);
                return newUpdate;
            } else if(updatePuzzle != null && !isSolved(updatedPuzzle)) { //not yet solved
                attemptedRepository.save(updatedPuzzle);
                return updatePuzzle;
            }
        }

        return null;
    }

    private Attempted updatePuzzle(Attempted userPuzzle, Attempted update) {
        UserPuzzleId puzzleId = userPuzzle.getId();
        Optional<Users> user = usersRepository.findById(puzzleId.getUsername());
        Optional<Puzzle> puzzle = puzzleRepository.findById(puzzleId.getPuzzleId());

        if(user.isPresent() && puzzle.isPresent()) {
            return new Attempted( //return updated UserPuzzle
                    puzzleId,
                    user.get(),
                    puzzle.get(),
                    update.getCurrentState(),
                    userPuzzle.getSecondsWorkedOn() + update.getSecondsWorkedOn(),
                    userPuzzle.getHintsUsed() + update.getHintsUsed(),
                    userPuzzle.getStartedOn()
            );
        }

        return null; //puzzle or user doesn't exist
    }

    private boolean isSolved(Attempted updates) {
        Optional<Puzzle> optPuzzle = puzzleRepository.findById(updates.getId().getPuzzleId());

        if(optPuzzle.isPresent()) {
            Puzzle puzzle = optPuzzle.get();

            return puzzle.getSolutionVals().equals(updates.getCurrentState());
        }

        return false;
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
