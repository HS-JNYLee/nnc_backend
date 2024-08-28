package com.eastflag.nnc.navigation;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private int caretakerId;

    @Column(nullable = false)
    private int caregiverId;

    private String transportRoute;

    private String route;
}
