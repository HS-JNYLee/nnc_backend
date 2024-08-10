package com.eastflag.nnc.testkmj.relation;

import jakarta.persistence.*;
import lombok.*;

/**
 * UserRelation Entity: user_relation Table 정보
 *
 * relationId (고유ID): 유저 식별자
 * caretakerId (피보호자ID): 피보호자 ID
 * caregiverId (보호자ID): 보호자 ID
 * relation (관계): 피보호자에 대한 보호자의 관계
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "user_relation")
public class UserRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int relationId;

    @Id
    @JoinColumn(name = "user_id")
    private int caretakerId;

    @Id
    @JoinColumn(name = "user_id")
    private int caregiverId;

    @Column(nullable = false)
    private String relation;
}
