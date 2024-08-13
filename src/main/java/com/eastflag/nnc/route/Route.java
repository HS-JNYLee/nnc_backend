package com.eastflag.nnc.route;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "route")
public class Route {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer route_id;

    @Embedded
    @Column(name = "location", nullable = false)
    private Location location;

    @Column(name = "cron", nullable = false)
    private String cron;
}
