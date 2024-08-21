package com.eastflag.nnc.pronearea;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accident_prone_area")
public class AccidentProneArea {
    @Id
    @Column(name = "accident_prone_area_fid")
    Integer accidentProneAreaFid;

    @Column(name = "accident_prone_area_id")
    Integer accidentProneAreaId;

    @Column(name = "beopjeongdong_code")
    String beopjeongdongCode;

    @Column(name = "jijeom_code")
    Integer jijeomCode;

    @Column(name = "address")
    String address;

    @Column(name = "jijeom_name")
    String jijeomName;

    @Column(name = "accident_count")
    Integer accidentCount;

    @Column(name = "casualty_count")
    Integer casualtyCount;

    @Column(name = "deaths_count")
    Integer deathsCount;

    @Column(name = "seriously_injured_count")
    Integer seriouslyInjuredCount;

    @Column(name = "minor_injured_count")
    Integer minorInjuredCount;

    @Column(name = "reported_injured_count")
    Integer reportedInjuredCount;

    @Column(name = "longitude")
    Double longitude;

    @Column(name = "latitude")
    Double latitude;

    @Column(name = "polygons")
    String polygons;
}
