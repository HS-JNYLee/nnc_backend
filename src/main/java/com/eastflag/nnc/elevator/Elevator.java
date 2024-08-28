package com.eastflag.nnc.elevator;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Table(name = "subway_elevator_location")
public class Elevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "엘레베이터 고유 번호", example = "1")
    @Column(name = "elevator_id")
    private int elevatorId;

    @Schema(description = "지하철 역이름", example = "강남역")
    @Column(name = "station_name")
    String stationName;

    // Lat Lon 순서
    @Schema(description = "엘리베이터 좌표 ", example = "37.649430188745555,127.01386997325214")
    @Column(name = "location")
    String location;

}
