package com.eastflag.nnc.testkmj.User;

import com.eastflag.nnc.testkmj.RoleId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor // 만들 필요가 있나 의문
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String name;

    @Column(unique = true, nullable = false)
    private String tel_num;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleId roleId;
}
