package com.example.SodokuBrainBackend.Users;


import com.example.SodokuBrainBackend.Users.Users;
import com.example.SodokuBrainBackend.Users.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }

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
}