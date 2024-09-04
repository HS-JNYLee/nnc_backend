package com.eastflag.nnc.navigation;

import jakarta.persistence.*;
import lombok.*;

/**
 * Navigation Entity: navigation Table 정보
 *
 * navigationId (고유ID): 내비게이션 식별자
 * caretakerId (사용자ID): 안내를 진행하는 사용자 식별자
 * caregiverId (보호자ID): 안내를 관찰하는 보호자 식별자
 * transportRoute (경로 정보): 경로에 대한 정보가 표시되는 객체 Json
 * route (경로): 경로 좌표 리스트 객체 Json
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "navigation")
public class Navigation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int navigationId;

    @Column(unique = true, nullable = false)
    private int caretakerId;

    @Column(unique = true, nullable = false)
    private int caregiverId;

    private String transportRoute;

    private String route;
}
