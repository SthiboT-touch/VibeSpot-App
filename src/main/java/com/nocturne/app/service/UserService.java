package com.nocturne.app.service;

import com.nocturne.app.model.User;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory user store for demo purposes.
 * Data resets every time the application restarts. Passwords are hashed
 * with SHA-256, which is fine for a learning project but should be swapped
 * for a proper library (e.g. Spring Security's BCryptPasswordEncoder) before
 * any real-world use.
 */
@Service
public class UserService {

    private final Map<String, User> usersByEmail = new LinkedHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(normalize(email)));
    }

    public boolean emailTaken(String email) {
        return usersByEmail.containsKey(normalize(email));
    }

    public User register(String name, String email, String rawPassword) {
        User user = new User(idSequence.getAndIncrement(), name, normalize(email),
                hash(rawPassword), LocalDate.now());
        usersByEmail.put(normalize(email), user);
        return user;
    }

    public Optional<User> authenticate(String email, String rawPassword) {
        User user = usersByEmail.get(normalize(email));
        if (user == null) {
            return Optional.empty();
        }
        return user.getPasswordHash().equals(hash(rawPassword)) ? Optional.of(user) : Optional.empty();
    }

    public void resetPassword(String email, String newRawPassword) {
        User user = usersByEmail.get(normalize(email));
        if (user != null) {
            user.setPasswordHash(hash(newRawPassword));
        }
    }

    private String normalize(String email) {
        return email == null ? "" : email.trim().toLowerCase();
    }

    private String hash(String raw) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
