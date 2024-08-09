package com.eastflag.nnc.testkmj.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String name;

    @Column(unique = true, nullable = false)
    private String telNum;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleId roleId;
}
