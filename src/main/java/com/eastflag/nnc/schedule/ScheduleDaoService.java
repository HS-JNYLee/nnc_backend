package com.eastflag.nnc.schedule;

import com.eastflag.nnc.exception.ControlledException;
import com.eastflag.nnc.fcm.FcmService;
import com.eastflag.nnc.fcm.request.Message;
import com.eastflag.nnc.fcm.request.MessageWrapper;
import com.eastflag.nnc.fcm.request.Notification;
import com.eastflag.nnc.fcm.Fcm;
import com.eastflag.nnc.fcm.FcmRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp ;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.eastflag.nnc.exception.errorcode.ScheduleErrorCode.*;

@Service
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleDaoService {

    // 여기서 DB 연결 작업
    //private static List<Schedule> sdl = new ArrayList<>(); //Sche Dule List
//    private static int user_id = 0;
//    private static int schedule_id =0;
//    private static int route_id = 0;
//
//    static {
//        sdl.add(new Schedule(++schedule_id,++user_id,"Apple", new Timestamp (System.currentTimeMillis()).toString(), new Timestamp(System.currentTimeMillis()).toString(), "과일사기", ++route_id));
//        sdl.add(new Schedule(++schedule_id,user_id,"Apple11", new Timestamp (System.currentTimeMillis()).toString(), new Timestamp(System.currentTimeMillis()).toString(), "과일사기11", ++route_id));
//        sdl.add(new Schedule(++schedule_id,user_id,"Apple22", new Timestamp (System.currentTimeMillis()).toString(), new Timestamp(System.currentTimeMillis()).toString(), "과일사기22", ++route_id));
//        sdl.add(new Schedule(++schedule_id,++user_id,"Banana", new Timestamp (System.currentTimeMillis()).toString(), new Timestamp(System.currentTimeMillis()).toString(), "과일사기", ++route_id));
//        sdl.add(new Schedule(++schedule_id,++user_id,"Orange", new Timestamp (System.currentTimeMillis()).toString(), new Timestamp(System.currentTimeMillis()).toString(), "과일사기", ++route_id));
//    }
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final ScheduleRepository sdl;
    private final FcmRepository fcmRepository;
    private final FcmService fcmService;
    private Gson gson = new Gson();

    public Schedule saveSchedule(Schedule schedule){

        sdl.save(schedule);

        return schedule;
    }

    public List<Schedule> getAllSchedule(int user_id){

        return sdl
                .findScheduleByUserId(user_id)
                .orElseThrow(() -> new ControlledException(NO_SCHEDULE));
    }

    // 값을 찾는 로직 - shceduleID 이용
    public Schedule findScheduleByScheduleID(int scheduleID){

        return sdl.findById(scheduleID)
                .orElseThrow(() -> new ControlledException(SCHEDULE_ID_NOT_FOUND));
    }

    // 값을 찾는 로직 - Datetime 이용
    public List<Schedule> getScheduleByDateTime(int userID, String dateTime) throws UnsupportedEncodingException {
        dateTime = URLDecoder.decode(dateTime, StandardCharsets.UTF_8);

        Timestamp check = Timestamp.valueOf(dateTime);

        Predicate<Schedule> findDate = sc-> check.getTime() >= Timestamp.valueOf(sc.getDateBegin()).getTime() && check.getTime() <= Timestamp.valueOf(sc.getDateEnd()).getTime();

        List<Schedule> target = sdl
                .findScheduleByUserId(userID)
                .orElseThrow(() -> new ControlledException(NO_SCHEDULE));

        target = target.stream().filter(findDate).collect(Collectors.toList());

        return target;
    }

    // schedule 삭제
    public Schedule removeSchedule(int scheduleID) {
        var ret = findScheduleByScheduleID(scheduleID);

        sdl.deleteById(scheduleID);

        return ret;
    }

    //schedule 수정
    public Schedule modify(Schedule schedule) {
        var target = findScheduleByScheduleID(schedule.getScheduleId());

        target.setTitle(schedule.getTitle());
        target.setDescription(schedule.getDescription());
        target.setDateBegin(schedule.getDateBegin());
        target.setDateEnd(schedule.getDateEnd());

        sdl.save(target);

        return target;
    }

    @Scheduled(fixedRate = 60000)
    public void checkSchedule() throws IOException {
        //현재 조회할 시간 가져오기
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter formatting = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = now.format(formatting);

        //System.out.println("Schedule System Called!!  :  " + now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        List<Schedule> schedules = sdl
                .findScheduleByDateBegin(formattedDateTime)
                .orElseThrow(() -> new ControlledException(NO_SCHEDULE_IN_DATETIME));

        for(Schedule res:schedules){
            log.info("userID : " + res.getUserId() + ", Title : " +  res.getTitle());
            Optional<Fcm> getFcm = fcmRepository.findByUserId(res.getUserId());

            if(getFcm.isPresent()){
                String token = getFcm.get().getFcmToken();

                String sendString = "일정/" + res.getTitle();

                fcmService.postMessage(
                    new MessageWrapper(
                        Message
                            .builder()
                            .token(token)
                            .notification(
                                Notification
                                    .builder()
                                    .title(sendString)
                                    .body(gson.toJson(res))
                                    .build()
                            ).build()));
            }
        }
    }

    /**
     * 뤼튼 발 DateTime 형식 검사 함수
     * @param dateTime 전달 받은 dateTime 문자열
     * @return dateTime(String)이 DateTime 형식으로 전달되었는지에 대한 참 거짓 
     */
    boolean isValidDateTime(String dateTime) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}