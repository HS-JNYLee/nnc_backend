package com.eastflag.nnc.fcm;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "fcm1")
public class Fcm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FcmId;

    @Column(unique = true, nullable = false)
    @JoinColumn(name = "user_id")
    private int userId;

    @Column(nullable = false)
    private String fcmToken;
}
