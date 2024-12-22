package com.example.SodokuBrainBackend.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Solved {
    @EmbeddedId
    private SolvedId id;

    /*@ManyToOne
    @JoinColumn(name = "Users", referencedColumnName = "username", insertable = false, updatable = false, nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "puzzle_id", referencedColumnName = "puzzle_id", insertable = false, updatable = false, nullable = false)
    private Puzzle puzzle;
*/
    @Column(name = "seconds_to_solve", nullable = false)
    private int secondsToSolve;

    @Column(name = "hints_used")
    private int hintsUsed;

    @Column(name = "started_on", nullable = false)
    private Date startedOn;

    @Column(name = "solved_on", nullable = false)
    private Date solvedOn;

    @Column(name = "rating")
    private Integer rating;

    public Solved(SolvedId id, /*Users user, Puzzle puzzle,*/ int secondsToSolve, int hintsUsed, Date startedOn, Date solvedOn, Integer rating) {

        this.id = id;
        //this.user = user;
        //this.puzzle = puzzle;
        this.secondsToSolve = secondsToSolve;
        this.hintsUsed = hintsUsed;
        this.startedOn = startedOn;
        this.solvedOn = solvedOn;
        this.rating = rating;
    }

    public Solved() {};

    public String getUsername() {
        return id.getUsername();
    }

    public void setUsername(String username) {
        id.setUsername(username);
    }

    public Long getPuzzleId() {
        return id.getPuzzleId();
    }

    public void setPuzzleId(Long puzzleId) { id.setPuzzleId(puzzleId); }

    public SolvedId getId() {
        return id;
    }

    public void setId(SolvedId id) {
        this.id = id;
    }

    /*
    public User getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }*/
/*
    public Puzzle getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Puzzle puzzle) {
        this.puzzle = puzzle;
    }
*/
    public int getSecondsToSolve() {
        return secondsToSolve;
    }

    public void setSecondsToSolve(int secondsToSolve) {
        this.secondsToSolve = secondsToSolve;
    }

    public int getHintsUsed() {
        return hintsUsed;
    }

    public void setHintsUsed(int hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    public Date getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(Date startedOn) {
        this.startedOn = startedOn;
    }

    public Date getSolvedOn() {
        return solvedOn;
    }

    public void setSolvedOn(Date solvedOn) {
        this.solvedOn = solvedOn;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
