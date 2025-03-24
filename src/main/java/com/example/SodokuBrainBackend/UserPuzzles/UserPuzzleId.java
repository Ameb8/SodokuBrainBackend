package com.example.SodokuBrainBackend.UserPuzzles;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserPuzzleId implements Serializable {
    private String username;
    private Long puzzleId;

    public UserPuzzleId(String username, Long puzzleId) {
        this.username = username;
        this.puzzleId = puzzleId;
    }

    public UserPuzzleId() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(Long puzzleId) {
        this.puzzleId = puzzleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserPuzzleId that = (UserPuzzleId) o;
        return Objects.equals(username, that.username) && Objects.equals(puzzleId, that.puzzleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, puzzleId);
    }
}
