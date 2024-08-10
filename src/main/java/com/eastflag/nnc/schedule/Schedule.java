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
    private int schedule_id;

    @JoinColumn(name = "user_id")
    private int user_id;

    private String title;
    private String date_begin;
    private String date_end;
    private String description;

    @JoinColumn(name = "route_id")
    private int route_id;
}
