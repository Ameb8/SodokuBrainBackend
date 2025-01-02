package com.example.SodokuBrainBackend.Puzzle.DTO;

import java.time.LocalDate;

public class PuzzleStateDTO {
    private Long puzzleId;
    private String currentState;
    private String username;
    private Integer secondsWorkedOn;
    private Integer hintsUsed;
    private LocalDate startedOn;
    private LocalDate SolvedOn;
    private Byte rating;

    public PuzzleStateDTO(Long puzzleId, String currentState, String username, Integer secondsWorkedOn, Integer hintsUsed, LocalDate startedOn, LocalDate solvedOn, Byte rating) {
        this.puzzleId = puzzleId;
        this.currentState = currentState;
        this.username = username;
        this.secondsWorkedOn = secondsWorkedOn;
        this.hintsUsed = hintsUsed;
        this.startedOn = startedOn;
        SolvedOn = solvedOn;
        this.rating = rating;
    }

    public Long getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Long puzzleId) {
        this.puzzleId = puzzleId;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getSecondsWorkedOn() {
        return secondsWorkedOn;
    }

    public void setSecondsWorkedOn(Integer secondsWorkedOn) {
        this.secondsWorkedOn = secondsWorkedOn;
    }

    public Integer getHintsUsed() {
        return hintsUsed;
    }

    public void setHintsUsed(Integer hintsUsed) {
        this.hintsUsed = hintsUsed;
    }

    public LocalDate getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }

    public LocalDate getSolvedOn() {
        return SolvedOn;
    }

    public void setSolvedOn(LocalDate solvedOn) {
        SolvedOn = solvedOn;
    }

    public Byte getRating() {
        return rating;
    }

    public void setRating(Byte rating) {
        this.rating = rating;
    }
}
