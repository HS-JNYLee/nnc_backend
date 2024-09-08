package com.eastflag.nnc.fcm.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message{
    /**
     * token을 제외한 나머지 데이터는 어떤 걸 넣어도 상관없음.
     */
    private String name; // 이름
    private String data; // 데이터
    private Notification notification; // 제목, 내용
    private String token; // 타겟팅할 엔드포인트 토큰
    private String topic; // 주제
    private String condition; // 조건
    private FcmOptions fcmOptions;
}
