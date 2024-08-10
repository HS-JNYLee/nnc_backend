package com.eastflag.nnc.schedule;

import com.eastflag.nnc.schedule.scheduleexception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.sql.Timestamp ;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Component
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
    private final ScheduleRepository sdl;

    public Schedule saveSchedule(Schedule schedule){

        if(findScheduleByScheduleID(schedule.getUser_id(),schedule.getSchedule_id())!=null){
            return schedule; // 값이 이미 존재 한다면, 아무런 동작X
        }

        Schedule tmp = schedule;
        sdl.save(tmp);
        return tmp;
    }

    public List<Schedule> getAllSchedule(int user_id){
        ArrayList<Schedule> res = new ArrayList<Schedule>();
        for(Schedule sc : sdl){
            if(sc.getUser_id()==user_id){
                res.add(sc);
            }
        }

        return res;
    }

    // 값을 찾는 로직 - shceduleID 이용
    public Schedule findScheduleByScheduleID(int userID, int scheduleID){
        Predicate<Schedule> findUserid = sc->sc.getSchedule_id()==scheduleID && sc.getUser_id()==userID;
        return sdl.stream().filter(findUserid).findFirst().orElse(null);
    }

    // 값을 찾는 로직 - Datetime 이용
    public List<Schedule> getScheduleByDateTime(int userID, String dateTime){

        Timestamp check = Timestamp.valueOf(dateTime);

        Predicate<Schedule> findUserid = sc -> sc.getUser_id()==userID;
        //Predicate<Schedule> findDate = sc-> check.after(Timestamp.valueOf(sc.getDate_begin())) && check.before(Timestamp.valueOf(sc.getDate_end()));
        Predicate<Schedule> findDate = sc-> check.getTime() >= Timestamp.valueOf(sc.getDate_begin()).getTime() && check.getTime() >= Timestamp.valueOf(sc.getDate_end()).getTime();

        List<Schedule> res = sdl.stream().filter(findUserid.and(findDate)).collect(Collectors.toList());

        return res;
    }

    // schedule 삭제
    public Schedule removeSchedule(int userID, int scheduleID) {
        Schedule del = findScheduleByScheduleID(userID, scheduleID);

        if(del==null){ // 삭제할 값이 없다면 값 삭제 메시지 전송
            throw new ScheduleNotFoundException("Remove Schedule Fail");
        }

        sdl.remove(del);

        return del;
    }

    //schedule 수정
    public Schedule modify(Schedule schedule) {
        Schedule target = findScheduleByScheduleID(schedule.getUser_id(), schedule.getSchedule_id());

        if(target==null){ // 수정할 값이 없다면 삭제 메시지 전송
            throw new ScheduleNotFoundException("Modify Schedule Fail : Schedule is Not Exist");
        }

        target.setTitle(schedule.getTitle());
        target.setDescription(schedule.getDescription());
        target.setDate_begin(schedule.getDate_begin());
        target.setDate_end(schedule.getDate_end());

        return target;
    }
}
