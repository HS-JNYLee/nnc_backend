package com.eastflag.nnc.schedule;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Repository;

@Repository
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "schedule")
@Builder
@Getter
@Setter
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;

    @JoinColumn(name = "user_id")
    private int userId;

    private String title;
    private String dateBegin;
    private String dateEnd;
    private String description;

    @JoinColumn(name = "route_id")
    private int routeId;
}
