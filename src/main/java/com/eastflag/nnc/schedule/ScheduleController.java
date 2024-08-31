package com.eastflag.nnc.schedule;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.exception.ControlledException;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.eastflag.nnc.exception.errorcode.BasicErrorCode.NOT_DATETIME;
import static com.eastflag.nnc.exception.errorcode.ScheduleException.*;

@RestController
@RequestMapping("/api/v1/schedules")
public class ScheduleController {

    private ScheduleDaoService scheduleDaoService;

    public ScheduleController(ScheduleDaoService sd){
        this.scheduleDaoService = sd;
    }

    // 새로운 Schedule을 추가
    @PostMapping("/insert")
    public CommonResponse addSchedule(@RequestBody Schedule schedule){
        Schedule res = this.scheduleDaoService.saveSchedule(schedule);

        return CommonResponse.builder().code(200).message("Add Success").data(res).build();
    }

    // 사용자의 전체 일정 반환
    @GetMapping("/allschedule/{userid}")
    public CommonResponse getAllSchedules(@PathVariable(name = "userid") int userid){
        List<Schedule> res = this.scheduleDaoService.getAllSchedule(userid);

        if(res.isEmpty()) throw new ControlledException(NO_SCHEDULE);

        return CommonResponse.builder().code(200).message("Find Success").data(res).build();
    }

    // userid와 scheduleid와 같은 schedule를 response
    @GetMapping("/find/usingscheduleid/{scheduleid}")
    public CommonResponse getScheduleByScheduleID(@PathVariable(name = "scheduleid") int scheduleid) {
        Schedule res = this.scheduleDaoService.findScheduleByScheduleID(scheduleid);

        return CommonResponse.builder().code(200).message("Find Success").data(res).build();
    }

    // userid와 datetime이 같은 schedule를 response (리스트로 반환)
    @GetMapping("/find/usingdatetime/{userid}/{datetime}")
    public CommonResponse getScheduleByDateTime(@PathVariable(name = "userid") int userid, @PathVariable(name = "datetime") String dateTime)
            throws UnsupportedEncodingException {
        List<Schedule> res;

        // TODO: dateTime에 대한 서식 에러처리가 되지 않아서 추가로 핸들링 해야한다.
        if(!scheduleDaoService.isValidDateTime(dateTime)) throw new ControlledException(NOT_DATETIME);

        dateTime = URLDecoder.decode(dateTime, StandardCharsets.UTF_8);
        res = this.scheduleDaoService.getScheduleByDateTime(userid, dateTime);

        if(res.isEmpty()) throw new ControlledException(NO_SCHEDULE_IN_DATETIME);

        return CommonResponse.builder()
                .code(200)
                .message("Find Success")
                .data(res)
                .build();
    }

    @PatchMapping("/modify")
    public CommonResponse modifySchedule(@RequestBody Schedule schedule){
        return CommonResponse.builder().code(200).message("Modify Complete").data(this.scheduleDaoService.modify(schedule)).build();
    }

    @DeleteMapping("/remove/{scheduleid}")
    public CommonResponse removeSchedule(@PathVariable(name = "scheduleid") int scheduleid){
        return CommonResponse.builder().code(200).message("Delete Complete").data(this.scheduleDaoService.removeSchedule(scheduleid)).build();
    }
}
