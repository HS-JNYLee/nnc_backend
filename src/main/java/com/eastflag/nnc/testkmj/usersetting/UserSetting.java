package com.eastflag.nnc.testkmj.usersetting;

import com.eastflag.nnc.testkmj.user.User;
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
@Table(name = "user_account")
public class UserSetting {
    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Boolean voiceGuide = true;

    @Column(nullable = false)
    private Boolean alertRoute = true;

    @Column(nullable = false)
    private Boolean alertDanger = true;

    @Column(nullable = false)
    private Boolean lowBus = false;

    @Column(nullable = false)
    private Boolean elevatorFirst = false;
}
