package com.eastflag.nnc.exception;

import com.eastflag.nnc.common.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.UnsupportedEncodingException;
import java.sql.SQLIntegrityConstraintViolationException;

import static com.eastflag.nnc.exception.errorcode.BasicErrorCode.*;

@Log4j2
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * ControlledException을 통해 팀원이 직접적으로 추가한 ErrorCode를 제어한다.
     * 
     * @param ex 반환된 ErrorCode 정보
     * @return exception.errorcode 패키지에 작성한 정보를 요청 클라이언트로 전송한다.
     */
    @ExceptionHandler(ControlledException.class)
    protected ResponseEntity handleControlledException(ControlledException ex) {
        var code = ex.getErrorCode().getStatus();
        var message = ex.getErrorCode().getMessage();

        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    /**
     * nncdb 내부에서 발생한 오류를 제어한다.
     * ex) id가 조회되지 않는다. 종복된 id를 생성한다. ect...
     *
     * @param ex 반환된 Error 정보 ※ 직접적인 원인은 SpringBoot log를 통해 조회가능
     * @return [404] 데이터베이스 접근 오류
     */
    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity handleDataAccessException(DataAccessException ex){
        var code = DATA_BASE_ERROR.getStatus();
        var message = DATA_BASE_ERROR.getMessage();

        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    /**
     * 디코딩 작업 중 발생한 오류를 제어한다.
     * ex) UrlDecoder 디코딩 실패 ect...
     *
     * @param ex 반환된 Error 정보 ※ 직접적인 원인은 SpringBoot log를 통해 조회가능
     * @return [415] 잘못된 형식으로 인코딩되었습니다.
     */
    @ExceptionHandler(UnsupportedEncodingException.class)
    protected ResponseEntity<CommonResponse> handleUnsupportedEncodingException(UnsupportedEncodingException ex) {
        var code = UNSUPPORTED_ENCODING.getStatus();
        var message = UNSUPPORTED_ENCODING.getMessage();

        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    /**
     * 요청의 매개변수가 유효성 검사 중 발생한 오류를 제어한다.
     * ※ 기존에 있었던 에러. 우리에게 필요한 핸들런지 모호함. TODO: 다 같이 추가할 것
     * ex) 유효성 검사 실패, 다른 타입 객체 반환 ect...
     *
     * @param ex 반환된 Error 정보 ※ 직접적인 원인은 SpringBoot log를 통해 조회가능
     * @return [400] 잘못된 타입의 객체가 전달되었습니다.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var code = TYPE_MISS.getStatus();
        var message = TYPE_MISS.getMessage();

        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    /**
     * 데이터베이스의 무결성 제약 조건이 위반되었을 때 발생한 오류를 제어한다.
     * 기존에 있었던 에러 ※ 우리에게 필요한 핸들런지 모호함.
     *
     * @param ex 반환된 Error 정보 ※ 직접적인 원인은 SpringBoot log를 통해 조회가능
     * @return [500] 참조 무결성에 위배되었습니다.
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<CommonResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        var code = REFERENTIAL_INTEGRITY_MISS.getStatus();
        var message = REFERENTIAL_INTEGRITY_MISS.getMessage();

        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    /**
     * 아무 Exception을 거치치 않고 반환된 ErrorException을 제어하는 핸들러
     * ex) 예측하지 못한 Error와 새로 제어해야 할 Exception 발생 시
     *
     * @param ex 반환된 Error 정보 ※ 직접적인 원인은 SpringBoot log를 통해 조회가능
     * @return [500] 에러 원인 불명
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleServerException(Exception ex) {
        var code = ERROR_NOT_FOUND.getStatus();
        var message = ERROR_NOT_FOUND.getMessage();

        log.info("#################### 예기치 못한 오류 발생 ####################");
        log.info("error code: " + code + " message: " + message);
        log.info("explain: " + ex.getMessage());

        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }
}
