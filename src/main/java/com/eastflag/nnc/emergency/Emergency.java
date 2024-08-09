package com.eastflag.nnc.emergency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emergency")
public class Emergency {

    @Id
    @Column(nullable = false, name = "user_id")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "tel_num")
    private String telNum;

    @Column(name = "address")
    private String address;
}
