package com.example.SodokuBrainBackend.Users;

import com.example.SodokuBrainBackend.Users.DTO.LeaderboardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users savedUser = usersService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/secured/me")
    public ResponseEntity<Users> getLoggedInUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = principal.getAttribute("email");
        Optional<Users> userOptional = usersService.getUserByEmail(email);

        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    // Get a user by username
    @GetMapping("/secured/{username}")
    public ResponseEntity<Optional<Users>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usersService.getUserByUsername(username));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        Optional<Users> user = usersService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //delete user account
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUserByUsername(@PathVariable String username) {
        usersService.deleteUserByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/leaderboard/{pageOffset}/{pageSize}")
    public List<LeaderboardDTO> getLeaderboard(@PathVariable long pageOffset, @PathVariable int pageSize) {
        return usersService.getLeaderboard(pageOffset, pageSize);
    }

    @GetMapping("/numUsers")
    public long getNumUsers() {
        return usersService.getNumUsers();
    }

}