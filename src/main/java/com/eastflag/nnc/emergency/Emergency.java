package com.eastflag.nnc.emergency;

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
@Table(name = "user_emergency")
public class Emergency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emergency_id", nullable = false)
    private Integer emergencyId;

    @Column(name = "user_id", nullable = false)
    @Schema(defaultValue = "1")
    private Integer userId = 0;

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn = "n";

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "tel_num")
    private String telNum;

    @Column(name = "address")
    private String address;

}
