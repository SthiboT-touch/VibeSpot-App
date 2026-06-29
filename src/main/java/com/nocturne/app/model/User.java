package com.nocturne.app.model;

import java.time.LocalDate;

public class User {

    private final Long id;
    private String name;
    private final String email;
    private String passwordHash;
    private String bio;
    private String favoriteGenre;
    private final LocalDate joinedOn;

    public User(Long id, String name, String email, String passwordHash, LocalDate joinedOn) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.joinedOn = joinedOn;
        this.bio = "New to the city, ready to find the night's best vibe.";
        this.favoriteGenre = "House";
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getFavoriteGenre() { return favoriteGenre; }
    public void setFavoriteGenre(String favoriteGenre) { this.favoriteGenre = favoriteGenre; }
    public LocalDate getJoinedOn() { return joinedOn; }
}
