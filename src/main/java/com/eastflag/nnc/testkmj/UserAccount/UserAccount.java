package com.eastflag.nnc.testkmj.UserAccount;

import java.sql.Timestamp;
import java.util.UUID;

public class UserAccount {
    private final UUID userId;
    private final String email;
    private final String password;
    private final String passwordSalt;
    private final String hashAlgorithmId;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public UserAccount(UUID userId, String email, String password, String passwordSalt, String hashAlgorithmId, Timestamp createdAt, Timestamp updatedAt) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.hashAlgorithmId = hashAlgorithmId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getUserId() {
        return userId;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPasswordSalt() {
        return passwordSalt;
    }
    public String getHashAlgorithmId() {
        return hashAlgorithmId;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
