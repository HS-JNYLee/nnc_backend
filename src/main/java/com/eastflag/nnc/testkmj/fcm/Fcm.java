package com.eastflag.nnc.testkmj.fcm;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "fcm")
public class Fcm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int FcmId;

    @Column(unique = true, nullable = false)
    @JoinColumn(name = "user_id")
    private int userId;

    @Column(unique = true, nullable = false)
    private String fcmToken;
}
