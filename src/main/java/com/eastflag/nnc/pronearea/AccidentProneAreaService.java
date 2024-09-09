package com.eastflag.nnc.pronearea;

import com.eastflag.nnc.common.CommonResponse;
import com.eastflag.nnc.common.ResponseMessage;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentProneAreaService {
    private final AccidentProneAreaRepository accidentProneAreaRepository;
    private final Gson gson = new Gson();
    @Autowired
    public AccidentProneAreaService(AccidentProneAreaRepository accidentProneAreaRepository) {
        this.accidentProneAreaRepository = accidentProneAreaRepository;
    }

    /**
     *
     * @param query
     * @return
     */
    public CommonResponse findAll(String query) {
        ArrayList<AccidentProneArea> result = new ArrayList<>();
        try {
            // 위험 지역 전부 가져옴
            List<AccidentProneArea> accidentProneAreas = accidentProneAreaRepository.findAll();
            Type coordinatesType = new TypeToken<Coordinates>() {}.getType();
            Coordinates coordinates = gson.fromJson(query, coordinatesType);

            // 받아온 경로에 대해
            for(Coordinate coordinate : coordinates.getCoordinates()) {
                // 모든 위험 지역 좌표에 대해
                for(AccidentProneArea accidentProneArea : accidentProneAreas) {
                    // 거리 계산
                    double distance = DistanceCalculator.calculate(coordinate.getLongitude(),coordinate.getLatitude(),
                            accidentProneArea.getLongitude(), accidentProneArea.getLatitude()) * 1000;
                    //30미터 보다 작으면서 리스트에 없는 경우
                    if(distance < 30 && !result.contains(accidentProneArea)){
                        // 추가
                        result.add(accidentProneArea);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CommonResponse.builder()
                .code(200)
                .message(ResponseMessage.SUCCESS)
                .data(result)
                .build();
    }
}
