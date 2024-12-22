package com.example.SodokuBrainBackend.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class SolvedId implements Serializable {
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "puzzle_id", nullable = false)
    private Long puzzleId;

    public SolvedId(String username, Long puzzleId) {
        this.username = username;
        this.puzzleId = puzzleId;
    }

    public SolvedId() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public Long getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Long puzzleId) {
        this.puzzleId = puzzleId;
    }

    @Override
    public boolean equals(Object other) {
        if(other.getClass() != getClass())
            return false;

        SolvedId otherId = (SolvedId) other;

        if(!otherId.username.equals(username))
            return false;

        if(!otherId.puzzleId.equals(puzzleId))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, puzzleId);
    }
}
