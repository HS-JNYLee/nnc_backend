package com.eastflag.nnc.testkmj.useraccount;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userAccountId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String passwordSalt;

    @Column(nullable = false)
    private String hashAlgorithmId;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = false)
    private String address;

    private String detailAddress;
}
