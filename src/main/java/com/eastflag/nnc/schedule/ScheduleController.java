package com.eastflag.nnc.schedule;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.schedule.scheduleexception.ScheduleNotFoundException;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    public List<Schedule> getAllSchedules(int userid){
        List<Schedule> res = this.scheduleDaoService.getAllSchedule(userid);

        if(res.isEmpty()){
            throw new ScheduleNotFoundException("Schedule is Not Exist");
        }

        return res;
    }

    // userid와 scheduleid와 같은 schedule를 response
    @GetMapping("/find/usingscheduleid/{scheduleid}")
    public CommonResponse getScheduleByScheduleID(@PathVariable(name = "scheduleid") int scheduleid) throws ScheduleNotFoundException {
        Schedule res = this.scheduleDaoService.findScheduleByScheduleID(scheduleid);

        return CommonResponse.builder().code(200).message("Find Success").data(res).build();
    }

    // userid와 datetime이 같은 schedule를 response (리스트로 반환)
    @GetMapping("/find/usingdatetime/{userid}/{datetime}")
    public List<Schedule> getScheduleByDateTime(@PathVariable(name = "userid") int userid, @PathVariable(name = "datetime") String dateTime) throws ScheduleNotFoundException, UnsupportedEncodingException {

        dateTime = URLDecoder.decode(dateTime, StandardCharsets.UTF_8.name());

        List<Schedule> res = this.scheduleDaoService.getScheduleByDateTime(userid, dateTime);

        if(res.isEmpty()){// 해당 날짜를 포함하는 일정이 없는 경우
            throw new ScheduleNotFoundException("Schedule Not Found in this DateTime");
        }

        return res;
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
