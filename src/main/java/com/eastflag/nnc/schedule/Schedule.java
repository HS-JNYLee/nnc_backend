package com.eastflag.nnc.schedule;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.type.descriptor.jdbc.TinyIntJdbcType;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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

    private String address;
    private Integer isWholeday;
    private String guideDatetime;
}
