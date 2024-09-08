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

    // 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emergency_id", nullable = false)
    private Integer emergencyId;

    // 긴급 연락처를 소유하고 있는 사용자 ID
    @Column(name = "user_id", nullable = false)
    @Schema(defaultValue = "1")
    private Integer userId;

    // 메인 화면에 띄워지는 여부 Y/N
    @Column(name = "bookmark_yn", columnDefinition = "CHAR(1) DEFAULT 'N'")
    @Schema(defaultValue = "N")
    private String bookmarkYn;

    // 삭제 여부 Y/N  *현재 미사용*
    @Column(name = "delete_yn", nullable = false, length = 1, columnDefinition = "CHAR(1) DEFAULT 'N'")
    @Schema(defaultValue = "N")
    private String deleteYn = "n";

    // 긴급 연락처의 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 긴급 연락처의 전화번호
    @Column(name = "tel_num")
    private String telNum;

    // 긴급 연락처의 주소
    @Column(name = "address")
    private String address;

    // 긴급 연락처의 프로필 사진(기본은 github 아이콘 사진)
    @Lob
    @Column(name = "file_data")
    private byte[] fileData; // 새로 추가된 컬럼
}
