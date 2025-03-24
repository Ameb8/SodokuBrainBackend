package com.example.SodokuBrainBackend.Users;

import com.example.SodokuBrainBackend.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);

    @Procedure(name = "GetLeaders")
    List<Object[]> GetLeaderboard(@Param("page_offset") Long pageOffset, @Param("page_size") int pageSize);

    @Procedure(name = "GetNumUsers")
    List<Object[]> GetNumUsers();

    boolean existsByUsername(String username);
}