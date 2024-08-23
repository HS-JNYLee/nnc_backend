package com.eastflag.nnc.exception;

import com.eastflag.nnc.common.CommonResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.eastflag.nnc.exception.errorcode.BasicErrorCode.DATA_BASE_ERROR;
import static com.eastflag.nnc.exception.errorcode.BasicErrorCode.INTERNAL_SERVER_ERROR;

@Log4j2
@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger LOG = Logger.getGlobal();

    @ExceptionHandler(ControlledException.class)
    protected ResponseEntity handleCustomException(ControlledException ex) {
        var code = ex.getErrorCode().getStatus();
        var message = ex.getErrorCode().getMessage();

        log.error("error code: " + code + " message: " + message);
        log.error("explain: " + ex.getMessage());

        LOG.info("error code: " + code + " message: " + message);
        LOG.info("explain: " + ex.getMessage());
        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleServerException(Exception ex) {
        var code = INTERNAL_SERVER_ERROR.getStatus();
        var message = INTERNAL_SERVER_ERROR.getMessage();

        log.error("----- 예기치 못한 오류 -----");
        log.error("error code: " + code + " message: " + message);
        log.error("explain: " + ex.getMessage());

        LOG.info("error code: " + code + " message: " + message);
        LOG.info("explain: " + ex.getMessage());
        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    @ExceptionHandler(DataAccessException.class)
    protected ResponseEntity handleDataAccessException(DataAccessException ex){
        var code = DATA_BASE_ERROR.getStatus();
        var message = DATA_BASE_ERROR.getMessage();

        log.error("error code: " + code + " message: " + message);
        log.error("explain: " + ex.getMessage());

        LOG.info("error code: " + code + " message: " + message);
        LOG.info("explain: " + ex.getMessage());
        return ResponseEntity.ok(CommonResponse.builder().code(code).message(message).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<CommonResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());

        LOG.info("MethodArgumentNotValidException: " + ex.getMessage());

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .collect(Collectors.toList());

        var result = CommonResponse.builder()
                .code(400)
                .message(errors.toString())
                .build();

        return ResponseEntity.ok(result);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    protected ResponseEntity<CommonResponse> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex) {
        log.error("SQLIntegrityConstraintViolationException: {}", ex.getMessage());

        LOG.info("SQLIntegrityConstraintViolationException: " + ex.getMessage());
        var result = CommonResponse.builder()
                .code(500)
                .message(ex.getMessage())
                .build();

        return ResponseEntity.ok(result);
    }
}
