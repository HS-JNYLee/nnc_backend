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
@Table(name = "subway_elevator")
public class Elevator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "엘레베이터 고유 번호", example = "1")
    @Column(name = "elevator_id")
    private int elevatorId;

    @Schema(description = "철도운영기관명", example = "코레일")
    @Column(name = "railway_operator_name")
    String railwayOperatorName;

    @Schema(description = "운영노선명", example = "경의중앙선")
    @Column(name = "operation_line_name")
    String operationLineName;

    @Schema(description = "역명", example = "홍대입구역")
    @Column(name = "station_name")
    String stationName;

    @Schema(description = "관리번호", example = "5")
    @Column(name = "management_number")
    int managementNumber;

    @Schema(description = "(근접)출입구 번호", example = "7")
    @Column(name = "nearest_exit_number")
    String nearestExitNumber;

    @Schema(description = "상세위치", example = "(1F) AK몰 입구 (B1) 7번 출입구 근처 (B2) 7번 출입구 앞")
    @Column(name = "detailed_location")
    String detailedLocation;

    @Schema(description = "사작층(지상/지하)", example = "지하")
    @Column(name = "starting_level_ground_or_underground")
    int startingLevelGroundOrUnderground;

    @Schema(description = "시작층(운행역층", example = "2")
    @Column(name = "starting_station_level")
    String startingStationLevel;

    @Schema(description = "종료층(지상/지하", example = "지상")
    @Column(name = "ending_level_ground_or_underground")
    int endingLevelGroundOrUnderground;

    @Schema(description = "종료층(운행역층)", example = "1")
    @Column(name = "ending_station_level")
    int endingStationLevel;

    @Schema(description = "정원(인원수)", example = "17")
    @Column(name = "capacity_persons")
    int capacityPersons;

    @Schema(description = "정원(중량)(kg)", example = "1150")
    @Column(name = "capacity_weight_kg")
    int capacityWeightKg;

    @Schema(description = "승강기 상태", example = "운행")
    @Column(name = "elevator_status")
    int elevatorStatus;

    @Schema(description = "승강기 일련번호", example = "0102-596")
    @Column(name = "elevator_serial_number")
    String elevatorSerialNumber;

    @Schema(description = "데이터 기준일자", example = "20240518")
    @Column(name = "data_reference_date")
    String dataReferenceDate;

    @Schema(description = "엘리베이터 좌표", example = "수정")
    @Column(name = "elevator_coordinates")
    int elevatorCoordinates;

}
