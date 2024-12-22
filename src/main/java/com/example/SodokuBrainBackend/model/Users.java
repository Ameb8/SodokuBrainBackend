package com.example.SodokuBrainBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Users {
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    @Column(name = "f_name", nullable = false)
    private String fName;
    @Column(name = "l_name", nullable = false)
    private String lName;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;


    public Users(String username, String fName, String lName, String password, String email) {
        this.username = username;
        this.fName = fName;
        this.lName = lName;
        this.password = password;
        this.email = email;
    }

    public Users() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }
}
