package com.example.SodokuBrainBackend.repository;

import com.example.SodokuBrainBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
}